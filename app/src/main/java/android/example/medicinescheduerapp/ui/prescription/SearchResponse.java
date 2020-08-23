package android.example.medicinescheduerapp.ui.prescription;

import android.example.medicinescheduerapp.Post;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
    @SerializedName("patient")
    private Post patient;

    public Post getPatient(){
        return patient;
    }

}
