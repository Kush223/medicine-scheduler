package android.example.medicinescheduerapp.ui.prescription;

import android.content.Context;
import android.content.SharedPreferences;
import android.example.medicinescheduerapp.Prescription;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.example.medicinescheduerapp.R;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class patientPrescribeFragment extends Fragment {
    Button addPrescription;
    private TextView Name;
    private TextView email;
    private TextView phone;
    private Bundle bundle;
    private ArrayList<Prescription> listItem;

    public patientPrescribeFragment(Bundle bn) {
        this.bundle=bn;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_patient_prescribe, container, false);
        addPrescription = root.findViewById(R.id.prescribe_med);

        Name =root.findViewById(R.id.patient_name);
        email =root.findViewById(R.id.patient_email);
        phone =root.findViewById(R.id.patient_phone);

        Name.setText(String.valueOf(bundle.getString("DataName")));
        email.setText(String.valueOf(bundle.getString("DataEmail")));
        phone.setText(String.valueOf(bundle.getString("DataPhone")));
        addPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_auth_container_1,new prescriptionFragment())
                        .commit();
            }
        });

//        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_patient_prescribe);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setHasFixedSize(true);
//
//        final patientPrescribeAdapter adapter = new patientPrescribeAdapter();
//        recyclerView.setAdapter(adapter);

        return root;
    }
}