package android.example.medicinescheduerapp;

public class Prescription {
    private String med_name;
    private String med_days;
    private String med_dosage;


    public Prescription( String med_name, String med_days, String med_dosage) {

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
