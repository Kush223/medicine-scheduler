package android.example.medicinescheduerapp.ui.prescription;

import android.content.Context;
import android.content.SharedPreferences;
import android.example.medicinescheduerapp.Prescription;
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
import android.widget.EditText;

import java.util.ArrayList;


public class addMedPresFragment extends Fragment {
    private EditText medicine_name;
    private EditText medicine_description;
    private EditText medicine_duration;
    private EditText medicine_dosage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_medicine, container, false);
        medicine_name= root.findViewById(R.id.medicine_name);
        medicine_description = root.findViewById(R.id.medicine_description);
        medicine_dosage = root.findViewById(R.id.medicine_dosage);
        medicine_duration = root.findViewById(R.id.medicine_duration);

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
        String medDes = medicine_description.getText().toString();
        String medDur = medicine_duration.getText().toString();
        String medDos = medicine_dosage.getText().toString();

        SharedPreferences info = getContext().getSharedPreferences("info",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = info.edit();
        editor.putString("medName",medName);
        editor.putString("medDur",medDur);
        editor.putString("medDos",medDos);
        editor.apply();

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_auth_container_1,new prescriptionFragment())
                .commit();

    }
}