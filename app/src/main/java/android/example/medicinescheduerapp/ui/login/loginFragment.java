package android.example.medicinescheduerapp.ui.login;

import android.example.medicinescheduerapp.R;
import android.example.medicinescheduerapp.ui.JsonPlaceholderApi;
import android.example.medicinescheduerapp.ui.Post;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class loginFragment extends Fragment  {

    private TextView loginResult;
    private EditText email;
    private EditText password;
    private CheckBox show_hide_password;
    private JsonPlaceholderApi jsonPlaceholderApi;
    private Button loginBtn;
    private TextView createaccount;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_login,container,false);
         show_hide_password = (CheckBox) root.findViewById(R.id.show_hide_password);
         email =(EditText) root.findViewById(R.id.login_emailid);
         password = (EditText) root.findViewById(R.id.login_password);
         loginResult=(TextView) root.findViewById(R.id.loginResult);
         loginBtn=(Button) root.findViewById(R.id.loginBtn);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();


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

        show_hide_password
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checked then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    loginResult.setText("Email or password not entered");
                    return;
                }
                loginUser();

            }
        });

        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createaccount =(TextView)view.findViewById(R.id.createAccount);
        createaccount.setOnClickListener(view1 -> {
            Fragment fragment =new SignupFragment();
            if(getActivity()!=null) {
                Log.v("TAG","Success");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_auth_container, new SignupFragment())
                        .commit();
            }
        });

    }

    private void loginUser(){
        String emailEntered = email.getText().toString();
        String passwordEntered = password.getText().toString();
        Post post = new Post(emailEntered,passwordEntered);
        Call<Post> call = jsonPlaceholderApi.signupUser(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    loginResult.setText("User not logged in");
                    return;
                }
                Post postResponse = response.body();
                loginResult.setText("Logged in");
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                loginResult.setText(t.getMessage());
            }
        });
    }
}
