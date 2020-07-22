package android.example.medicinescheduerapp.ui;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JsonPlaceholderApi {
    @POST("doctors/login")
    Call<Post> loginDoctor(@Body Post post);

    @POST("doctors/signup")
    Call<Post> signupDoctor(@Body Post post);

    @POST("patients/login")
    Call<Post> loginPatient(@Body Post post);

    @POST("patients/signup")
    Call<Post> signupPatient(@Body Post post);

    @GET("users")
    Call<List<Post>> getAllUsers();

    @Headers({"Content-Type:application/json; charset=UTF-8","Accept:application/json"})
    @POST("doctors/patient/find")
    Call<Post> findPatient(@Header("Authorization") String header,@Body Post post);

    @Headers({"Content-Type:application/json; charset=UTF-8","Accept:application/json"})
    @POST("patients/doctor/find")
    Call<Post> findDoctor(@Header("Authorization") String header, @Body Post post);

}
