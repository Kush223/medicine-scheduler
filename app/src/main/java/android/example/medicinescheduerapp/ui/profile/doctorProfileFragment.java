package android.example.medicinescheduerapp.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.example.medicinescheduerapp.ui.JsonPlaceholderApi;
import android.example.medicinescheduerapp.ui.Post;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.medicinescheduerapp.R;
import android.widget.Button;
import android.widget.EditText;
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
import retrofit2.http.POST;


public class doctorProfileFragment extends Fragment {
    private Button searchPatient;
    private EditText searchEdit;
    private JsonPlaceholderApi jsonPlaceholderApi;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_doctor_profile,container,false);
        searchPatient = root.findViewById(R.id.search_patient);
        searchEdit = root.findViewById(R.id.search_p_edit);

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

        searchPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPatient();
            }
        });
        return root;
    }
    private void findPatient(){
        String emailEntered = searchEdit.getText().toString();
        SharedPreferences logged = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        String token = logged.getString("token","Null");
        Post post = new Post(emailEntered,null,null);
        Call<Post> call = jsonPlaceholderApi.findPatient("Bearer "+token,post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(),"Patient not found",Toast.LENGTH_SHORT).show();
                    return;
                }
                Post postResponse = response.body();
                Toast.makeText(getContext(),"Patient found",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
