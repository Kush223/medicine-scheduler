package android.example.medicinescheduerapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.example.medicinescheduerapp.R;
import android.example.medicinescheduerapp.ui.docPrescriptions.DocPrescriptionsFragment;
import android.os.Bundle;

public class getPresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pres);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_auth_container_2,new DocPrescriptionsFragment())
                .commit();
    }
}