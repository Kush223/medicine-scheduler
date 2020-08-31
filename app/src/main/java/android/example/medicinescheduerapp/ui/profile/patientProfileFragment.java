package android.example.medicinescheduerapp.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.example.medicinescheduerapp.JsonPlaceholderApi;
import android.example.medicinescheduerapp.Post;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.medicinescheduerapp.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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


public class patientProfileFragment extends Fragment {
    private EditText searchEdit;
    private Button searchDoctor;
    private JsonPlaceholderApi jsonPlaceholderApi;
    private TextView patientName;
    private TextView patientRecords;
    private TextView patientPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_patient_profile, container, false);
        searchDoctor= root.findViewById(R.id.search_doctor);
        searchEdit = root.findViewById(R.id.search_d_edit);
        patientName=root.findViewById(R.id.patient_name);
        patientRecords= root.findViewById(R.id.patient_records);
        patientPhone= root.findViewById(R.id.patient_phone);


        SharedPreferences info = getContext().getSharedPreferences("info",Context.MODE_PRIVATE);
        String name= info.getString("name",null);
        String phone = info.getString("phone",null);
        patientName.setText(name);
        patientPhone.setText(phone);

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


        searchDoctor.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findDoctor();
            }
        });
        return root;
    }
    private void findDoctor(){
        String emailEntered = searchEdit.getText().toString();
        SharedPreferences logged = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        String token = logged.getString("token","Null");
        Post post = new Post(emailEntered,null,null,null,null,null,null);
        Call<Post> call = jsonPlaceholderApi.findDoctor("token "+token,post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(),"Doctor not found",Toast.LENGTH_SHORT).show();
                    return;
                }
                Post postResponse = response.body();
                Toast.makeText(getContext(),"Doctor found",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
