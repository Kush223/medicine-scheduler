package android.example.medicinescheduerapp.ui.docPrescriptions;

import android.content.Context;
import android.content.SharedPreferences;
import android.example.medicinescheduerapp.JsonPlaceholderApi;
import android.example.medicinescheduerapp.Post;
import android.example.medicinescheduerapp.ui.LoadDialog;
import android.example.medicinescheduerapp.ui.prescription.Medicines;
import android.example.medicinescheduerapp.ui.prescription.PrescriptionPost;
import android.example.medicinescheduerapp.ui.prescription.SearchResponse;
import android.example.medicinescheduerapp.ui.prescription.prescriptionFragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.example.medicinescheduerapp.R;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DocPrescriptionsFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<PrescriptionPost> mlistview;
    private List<Medicines> medlist;
    private JsonPlaceholderApi jsonPlaceholderApi;
    private LoadDialog loadDialog;
    private  DocPresAdapter adapter;
    private String token;
    private List<PrescriptionPost> prescriptions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_doc_prescriptions, container, false);
        loadDialog = new LoadDialog(getActivity());
        mlistview = new ArrayList<>();


        recyclerView = root.findViewById(R.id.recycler_view_doc_prescriptions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://darshil.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);

        SharedPreferences info = getContext().getSharedPreferences("info", Context.MODE_PRIVATE);
        token = info.getString("token", "Null");

        String email = getActivity().getIntent().getStringExtra("email");
        loadDialog.startLoad();
        Post post = new Post(email, null, null, null, null, null, null);
        Call<Prescriptions> call = jsonPlaceholderApi.getPrescriptionsOfAPatient("Bearer " + token, post);
        call.enqueue(new Callback<Prescriptions>() {
            @Override
            public void onResponse(Call<Prescriptions> call, Response<Prescriptions> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Couldn't find prescriptions", Toast.LENGTH_SHORT).show();
                    loadDialog.dismissLoad();
                    return;
                }
                loadDialog.dismissLoad();

                prescriptions = response.body().getPrescriptions();
                if(prescriptions==null){
                    Toast.makeText(getContext(),"No prescriptions available",Toast.LENGTH_SHORT).show();
                }
                else{
                    for (PrescriptionPost prescription : prescriptions) {
                        Log.d("ID",prescription.getId());
                        medlist = new ArrayList<>();
                        for (Medicines medicine : prescription.getPres()) {
                            medlist.add(new Medicines(medicine.getMed_name(), medicine.getMed_days(), medicine.getMed_dosage()));
                        }
                        mlistview.add(new PrescriptionPost(prescription.getEmail(), prescription.getDate()
                                , prescription.getWeight(), prescription.getSymptoms(), medlist,prescription.getId()));
                    }

                }

                attach_doc_pres_adapter();
            }

            @Override
            public void onFailure(Call<Prescriptions> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                loadDialog.dismissLoad();
            }
        });

//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//                return 0;
//            }
//
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                mlistview.remove(viewHolder.getAdapterPosition());
//                adapter.notifyDataSetChanged();
//            }
//        }).attachToRecyclerView(recyclerView);

        return root;
    }



    private void attach_doc_pres_adapter(){
        adapter = new DocPresAdapter(mlistview, getActivity());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new DocPresAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PrescriptionPost prescriptionPost) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_auth_container_2,new DocPatientsFragment())
                        .commit();
            }

            @Override
            public void onDeleteClick(PrescriptionPost prescriptionPost, int position) {
//              PrescriptionPost swipedPrescription = adapter.getItemAt(viewHolder.getAdapterPosition());
                String id = adapter.getItemAt(position).getId();
                Log.d("ID","Id is "+id);
                Call<Void> call1= jsonPlaceholderApi.deletePrescription(id,"Bearer "+token);
                call1.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        mlistview.remove(position);
                        adapter.notifyItemRemoved(position);
                        //Log.d("body",response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}