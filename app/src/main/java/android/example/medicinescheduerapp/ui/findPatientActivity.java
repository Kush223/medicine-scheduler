package android.example.medicinescheduerapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.example.medicinescheduerapp.R;
import android.example.medicinescheduerapp.ui.login.loginFragment;
import android.example.medicinescheduerapp.ui.prescription.patientPrescribeFragment;
import android.example.medicinescheduerapp.ui.profile.doctorProfileFragment;
import android.example.medicinescheduerapp.ui.profile.patientProfileFragment;
import android.os.Bundle;

public class findPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_patient);

        Bundle bn =getIntent().getExtras();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_auth_container_1,new patientPrescribeFragment(bn))
                .commit();
    }
}