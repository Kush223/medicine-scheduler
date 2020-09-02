package android.example.medicinescheduerapp.ui.prescription;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrescriptionPost {
    @SerializedName("email")
    private String email;
    @SerializedName("date")
    private String date;
    @SerializedName("weight")
    private int weight;
    @SerializedName("symptoms")
    private String symptoms;
    @SerializedName("medicine")
    private List<Prescription> pres;

    public PrescriptionPost(String email, String date, int weight, String symptoms,List<Prescription> pres) {
        this.email = email;
        this.date = date;
        this.weight = weight;
        this.symptoms = symptoms;
        this.pres =pres;
    }

    public String getEmail() { return email; }

    public String getDate() { return date; }

    public int getWeight() { return weight; }

    public String getSymptoms() { return symptoms; }

    public List<Prescription> getPres(){return pres;}
}
