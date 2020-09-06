package android.example.medicinescheduerapp.ui.docPrescriptions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.example.medicinescheduerapp.JsonPlaceholderApi;
import android.example.medicinescheduerapp.Post;
import android.example.medicinescheduerapp.ui.LoadDialog;
import android.example.medicinescheduerapp.ui.getPresActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class DocPatientsFragment extends Fragment {
private JsonPlaceholderApi jsonPlaceholderApi;
private RecyclerView recyclerView;
private List<Post> mlistview;
private LoadDialog loadDialog;
private List<Post> patients;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.fragment_doc_patients, container, false);
        loadDialog=new LoadDialog(getActivity());
        mlistview= new ArrayList<Post>();
        recyclerView = root.findViewById(R.id.recycler_view_patient);
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

        loadDialog.startLoad();
        SharedPreferences info = getContext().getSharedPreferences("info", Context.MODE_PRIVATE);
        String token = info.getString("token","Null");

        Call<Patients> call = jsonPlaceholderApi.getPatients("Bearer "+token);
        call.enqueue(new Callback<Patients>() {
            @Override
            public void onResponse(Call<Patients> call, Response<Patients> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(),"Couldn't find patients",Toast.LENGTH_SHORT).show();
                    loadDialog.dismissLoad();
                    return;
                }
                loadDialog.dismissLoad();
                patients = response.body().getPatients();
                if(patients==null){
                    Toast.makeText(getContext(),"You do not have any patients currently",Toast.LENGTH_LONG).show();
                }
                else {
                    for (Post patient : patients) {
                        mlistview.add(new Post(patient.getEmail(), null, null, patient.getName(), patient.getPhone(), null, null));
                    }
                    attach_patient_adapter(mlistview);
                }
            }

            @Override
            public void onFailure(Call<Patients> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                loadDialog.dismissLoad();
            }
        });
        return root;
    }
    private void attach_patient_adapter(List<Post> list){
        final AllPatientsAdapter allPatientsAdapter = new AllPatientsAdapter(list,getActivity());
        recyclerView.setAdapter(allPatientsAdapter);

        allPatientsAdapter.setOnItemClickListener(new AllPatientsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Post post) {
                Intent intent = new Intent(getActivity(), getPresActivity.class);
                intent.putExtra("email",post.getEmail());
                startActivity(intent);
            }
        });
    }
}