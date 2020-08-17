package android.example.medicinescheduerapp;

public class Prescription {
    private String patient_email;
    private String med_name;
    private String med_days;
    private String med_dosage;
    private String patient_weight;
    private String patient_symptoms;
    private String med_extra_info;

    public Prescription(String patient_email, String med_name, String med_days, String med_dosage, String patient_weight, String patient_symptoms, String med_extra_info) {
        this.patient_email = patient_email;
        this.med_name = med_name;
        this.med_days = med_days;
        this.med_dosage = med_dosage;
        this.patient_weight = patient_weight;
        this.patient_symptoms = patient_symptoms;
        this.med_extra_info = med_extra_info;
    }

    public String getMed_extra_info(){
        return med_extra_info;
    }

    public String getPatient_email() {
        return patient_email;
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

    public String getPatient_weight() {
        return patient_weight;
    }

    public String getPatient_symptoms() {
        return patient_symptoms;
    }
}
