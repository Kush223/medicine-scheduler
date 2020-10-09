package android.example.medicinescheduerapp.ui.prescription;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrescriptionPost {
    @SerializedName("email")
    private String email;
    @SerializedName("Date")
    private String date;
    @SerializedName("weight")
    private int weight;
    @SerializedName("symptoms")
    private String symptoms;
    @SerializedName("medicines")
    private List<Medicines> pres;
    @SerializedName("_id")
    private String id;

    public PrescriptionPost(String email, String date, int weight, String symptoms,List<Medicines> pres,String id) {
        this.email = email;
        this.date = date;
        this.weight = weight;
        this.symptoms = symptoms;
        this.pres =pres;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public String getEmail() { return email; }

    public String getDate() { return date; }

    public int getWeight() { return weight; }

    public String getSymptoms() { return symptoms; }

    public List<Medicines> getPres(){return pres;}
}
