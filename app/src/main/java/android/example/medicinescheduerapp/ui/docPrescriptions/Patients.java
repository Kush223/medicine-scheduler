package android.example.medicinescheduerapp.ui.docPrescriptions;

import android.example.medicinescheduerapp.Post;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Patients {
    @SerializedName("patients")
    private List<Post> patients;

    public List<Post> getPatients() {
        return patients;
    }
}
