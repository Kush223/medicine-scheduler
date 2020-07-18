package android.example.medicinescheduerapp.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.medicinescheduerapp.R;
import android.widget.Button;
import android.widget.EditText;


public class patientProfileFragment extends Fragment {
    EditText searchEdit;
    Button searchDoctor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_patient_profile, container, false);
        searchDoctor= root.findViewById(R.id.search_doctor);
        searchEdit = root.findViewById(R.id.search_d_edit);

        searchDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return root;
    }
}
