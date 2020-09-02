package android.example.medicinescheduerapp.ui.docPrescriptions;

import android.content.Context;
import android.content.SharedPreferences;
import android.example.medicinescheduerapp.JsonPlaceholderApi;
import android.example.medicinescheduerapp.ui.prescription.Prescription;
import android.example.medicinescheduerapp.ui.prescription.PrescriptionPost;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.example.medicinescheduerapp.R;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DocPrescriptions extends Fragment {
private JsonPlaceholderApi jsonPlaceholderApi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.fragment_doc_prescriptions, container, false);

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

        Call<PrescriptionPost> call=jsonPlaceholderApi.getPrescription("Bearer"+token);
        call.enqueue(new Callback<PrescriptionPost>() {
            @Override
            public void onResponse(Call<PrescriptionPost> call, Response<PrescriptionPost> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText()
                }
            }

            @Override
            public void onFailure(Call<PrescriptionPost> call, Throwable t) {

            }
        });
        return root;
    }
}