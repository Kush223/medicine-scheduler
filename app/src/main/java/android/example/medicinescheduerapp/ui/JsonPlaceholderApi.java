package android.example.medicinescheduerapp.ui;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceholderApi {
    @POST("users/login")
    Call<Post> loginUser(@Body Post post);

    @POST("users/signup")
    Call<Post> signupUser(@Body Post post);

    @GET("users")
    Call<List<Post>> getAllUsers();

}
