package android.example.medicinescheduerapp.ui.prescription;

import com.google.gson.annotations.SerializedName;

public class Medicines {
    @SerializedName("name")
    private String med_name;
    @SerializedName("numberOfDays")
    private String med_days;
    @SerializedName("dosage")
    private String med_dosage;


    public Medicines(String med_name, String med_days, String med_dosage) {

        this.med_name = med_name;
        this.med_days = med_days;
        this.med_dosage = med_dosage;

    }


    public String getMed_name() {
        return med_name;
    }

    public String getMed_days() {
        return med_days;
    }

    public String getMed_dosage() {
        return med_dosage;
    }


}
