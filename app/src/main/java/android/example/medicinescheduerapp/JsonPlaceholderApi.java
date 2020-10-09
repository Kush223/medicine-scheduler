package android.example.medicinescheduerapp;

import android.example.medicinescheduerapp.ui.docPrescriptions.Patients;
import android.example.medicinescheduerapp.ui.docPrescriptions.Prescriptions;
import android.example.medicinescheduerapp.ui.prescription.PrescriptionPost;
import android.example.medicinescheduerapp.ui.prescription.SearchResponse;

import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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
    Call<SearchResponse> findPatient(@Header("Authorization") String header, @Body Post post);

    @Headers({"Content-Type:application/json; charset=UTF-8","Accept:application/json"})
    @POST("patients/doctor/find")
    Call<Post> findDoctor(@Header("Authorization") String header, @Body Post post);

    @POST("records/add")
    Call<PrescriptionPost> addPrescription(@Header("Authorization") String header, @Body PrescriptionPost prescriptionPost);

    @GET("records/fetch")
    Call<PrescriptionPost> getPrescription(@Header("Authorization") String header,@Body String email);

    @GET("records/get/patients")
    Call<Patients> getPatients(@Header("Authorization") String header);

    @POST("records/fetch/doctor")
    Call<Prescriptions> getPrescriptionsOfAPatient(@Header("Authorization") String header, @Body Post email);

    @PUT("records/update/{id}")
    Call<PrescriptionPost> updatePrescription(@Path("id") int id, @Header("Authorization") String header );

    @DELETE("records/delete/{id}")
    Call<Void> deletePrescription(@Path("id") String id, @Header("Authorization") String header );

}
