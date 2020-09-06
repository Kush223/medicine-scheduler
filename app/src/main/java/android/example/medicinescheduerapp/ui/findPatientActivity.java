package android.example.medicinescheduerapp.ui;

import android.example.medicinescheduerapp.ui.prescription.Medicines;
import android.example.medicinescheduerapp.R;
import android.example.medicinescheduerapp.ui.prescription.patientPrescribeFragment;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class findPatientActivity extends AppCompatActivity {

    public static int buttonCount=0;
    public static List<Medicines> mlistItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_patient);

        mlistItem = new ArrayList<Medicines>();
        Bundle bn =getIntent().getExtras();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_auth_container_1,new patientPrescribeFragment(bn))
                .commit();
    }
}