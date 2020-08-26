package android.example.medicinescheduerapp.ui.prescription;

import android.content.Context;
import android.content.SharedPreferences;
import android.example.medicinescheduerapp.Prescription;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.example.medicinescheduerapp.R;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class patientPrescribeFragment extends Fragment {

    FloatingActionButton addPrescription;

    private static Bundle bundle;
    Button AaddPrescription;

    private TextView Name;
    private TextView email;
    private TextView phone;
    private ArrayList<Prescription> listItem;

    public patientPrescribeFragment(Bundle bn) {
        this.bundle=bn;
    }

    public static Bundle getBundle(){
        return bundle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_patient_prescribe, container, false);
        addPrescription = root.findViewById(R.id.prescribe_med);

        getActivity().setTitle("Search Results");

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
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.close_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_close: close();
            return true;
            default:return super.onOptionsItemSelected(item);
        }
    }
    private void close(){
        getActivity().finish();
    }
}