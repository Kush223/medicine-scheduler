package android.example.medicinescheduerapp.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.example.medicinescheduerapp.JsonPlaceholderApi;
import android.example.medicinescheduerapp.Post;
import android.example.medicinescheduerapp.ui.findPatientActivity;
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


public class doctorProfileFragment extends Fragment {
    private Button searchPatient;
    public EditText searchEdit;
    private JsonPlaceholderApi jsonPlaceholderApi;
    private TextView doc_name;
    private TextView doc_experience;
    private TextView doc_field;
    private TextView doc_available_days;
    private TextView doc_time;
    private TextView doc_address;
    private TextView doc_phone;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_doctor_profile,container,false);
        searchPatient = root.findViewById(R.id.search_patient);
        searchEdit = root.findViewById(R.id.search_p_edit);
        doc_name= root.findViewById(R.id.doctor_name);
        doc_experience= root.findViewById(R.id.doctor_experience);
        doc_field = root.findViewById(R.id.doctor_field);
        doc_available_days = root.findViewById(R.id.doctor_available_days);
        doc_time = root.findViewById(R.id.doctor_time);
        doc_address = root.findViewById(R.id.doctor_address);
        doc_phone = root.findViewById(R.id.doctor_phone);

        SharedPreferences info = getContext().getSharedPreferences("info",Context.MODE_PRIVATE);
        String name = info.getString("name",null);
        String field = info.getString("field",null);
        String phone = info.getString("phone",null);
        String address = info.getString("address",null);

        doc_name.setText(name);
        doc_field.setText(field);
        doc_phone.setText(phone);
        doc_address.setText(address);

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
        SharedPreferences info = getContext().getSharedPreferences("info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = info.edit();
        editor.putString("patientEmail",emailEntered);
        editor.apply();
        String token = info.getString("token","Null");
        Post post = new Post(emailEntered,null,null,null,null,null,null);
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

                Intent intent = new Intent(getActivity(), findPatientActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
