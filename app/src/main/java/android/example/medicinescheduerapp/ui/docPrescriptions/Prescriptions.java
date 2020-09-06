package android.example.medicinescheduerapp.ui.docPrescriptions;

import android.example.medicinescheduerapp.ui.prescription.PrescriptionPost;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Prescriptions {
    @SerializedName("records")
    List<PrescriptionPost> prescriptions;

    public List<PrescriptionPost> getPrescriptions() {
        return prescriptions;
    }
}
