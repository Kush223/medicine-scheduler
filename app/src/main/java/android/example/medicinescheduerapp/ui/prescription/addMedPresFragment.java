package android.example.medicinescheduerapp.ui.prescription;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.example.medicinescheduerapp.R;
import android.widget.Button;
import android.widget.EditText;


public class addMedPresFragment extends Fragment {
    private EditText medicine_name;
    private EditText medicine_duration;
    private EditText medicine_dosage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_medicine, container, false);
        medicine_name= root.findViewById(R.id.medicine_name);
        medicine_dosage = root.findViewById(R.id.medicine_dosage);
        medicine_duration = root.findViewById(R.id.medicine_duration);

        Button backButton = root.findViewById(R.id.backpress);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_auth_container_1,new prescriptionFragment())
                        .commit();
            }
        });
        Button saveButton = root.findViewById(R.id.save_med);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMed();
            }
        });

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.save_pres_menu,menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save_pres: addMed();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
    private void addMed(){
        String medName = medicine_name.getText().toString();
        String medDur = medicine_duration.getText().toString();
        String medDos = medicine_dosage.getText().toString();

        Bundle args = new Bundle();
        args.putString("medname",medName);
        args.putString("meddur",medDur);
        args.putString("meddos",medDos);

        prescriptionFragment pres = new prescriptionFragment();
        pres.setArguments(args);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_auth_container_1,pres)
                .commit();

    }
}