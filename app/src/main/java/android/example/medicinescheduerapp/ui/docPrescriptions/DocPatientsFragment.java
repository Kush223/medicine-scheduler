package android.example.medicinescheduerapp.ui.docPrescriptions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.example.medicinescheduerapp.JsonPlaceholderApi;
import android.example.medicinescheduerapp.Post;
import android.example.medicinescheduerapp.ui.getPresActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.example.medicinescheduerapp.R;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DocPatientsFragment extends Fragment {
private JsonPlaceholderApi jsonPlaceholderApi;
private RecyclerView recyclerView;
private List<Post> mlistview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.fragment_doc_patients, container, false);
        mlistview= new ArrayList<Post>();

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
        String token = info.getString("token","Null");



        recyclerView = root.findViewById(R.id.recycler_view_patient);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        mlistview.add(new Post("wfs",null,null,"hgi","jbubj",null,null ));

        final AllPatientsAdapter allPatientsAdapter = new AllPatientsAdapter(mlistview,getActivity());
        recyclerView.setAdapter(allPatientsAdapter);

        allPatientsAdapter.setOnItemClickListener(new AllPatientsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Post post) {
//                Call<PrescriptionPost> call=jsonPlaceholderApi.getPrescription("Bearer"+token);
//                call.enqueue(new Callback<PrescriptionPost>() {
//                    @Override
//                    public void onResponse(Call<PrescriptionPost> call, Response<PrescriptionPost> response) {
//                        if(!response.isSuccessful()){
//                            //Toast.makeText()
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<PrescriptionPost> call, Throwable t) {
//
//                    }
//                });
                Intent intent = new Intent(getActivity(), getPresActivity.class);
                startActivity(intent);


            }
        });


        return root;
    }
}