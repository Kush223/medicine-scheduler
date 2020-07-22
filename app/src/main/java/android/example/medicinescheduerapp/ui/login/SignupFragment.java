package android.example.medicinescheduerapp.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.example.medicinescheduerapp.R;
import android.example.medicinescheduerapp.ui.JsonPlaceholderApi;
import android.example.medicinescheduerapp.ui.Post;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupFragment extends Fragment {

    private Context context;
    private EditText userEmailId;
    private EditText userPassword;
    private Button signupBtn;
    private JsonPlaceholderApi jsonPlaceholderApi;
    private CheckBox docCheckbox;
    private CheckBox patCheckbox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getContext()!=null)
        {
            this.context = getContext();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_signup,container,false);
        TextView alreadyUser =(TextView)root.findViewById(R.id.already_user);
        userEmailId = (EditText) root.findViewById(R.id.userEmailId);
        userPassword= (EditText) root.findViewById(R.id.password);
        signupBtn = (Button) root.findViewById(R.id.signUpBtn);
        docCheckbox=(CheckBox) root.findViewById(R.id.doctor_checkbox);
        patCheckbox=(CheckBox) root.findViewById(R.id.patient_checkbox);

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

        alreadyUser.setOnClickListener(v -> {
            if(getActivity()!=null) {
                Log.v("TAG","success");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_auth_container, new loginFragment())
                        .commit();
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userEmailId.getText().toString().isEmpty() || userPassword.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Email or password not entered",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(docCheckbox.isChecked()){
                    signupDoctor();
                }
                if(patCheckbox.isChecked()){
                    signupPatient();
                }
            }
        });
        return root;
    }
    private void signupDoctor(){
        String emailEntered = userEmailId.getText().toString();
        String passwordEntered= userPassword.getText().toString();
        Post post = new Post(emailEntered,passwordEntered,null);
        Call<Post> call = jsonPlaceholderApi.signupDoctor(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(),"User not signed in",Toast.LENGTH_SHORT).show();
                    return;
                }
                Post postResponse = response.body();

                Toast.makeText(getContext(),"Signed in",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_auth_container,new loginFragment())
                        .commit();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void signupPatient(){
        String emailEntered = userEmailId.getText().toString();
        String passwordEntered= userPassword.getText().toString();
        Post post = new Post(emailEntered,passwordEntered,null);
        Call<Post> call = jsonPlaceholderApi.signupPatient(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(),"User not signed in",Toast.LENGTH_SHORT).show();
                    return;
                }
                Post postResponse = response.body();

                Toast.makeText(getContext(),"Signed in",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_auth_container,new loginFragment())
                        .commit();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
