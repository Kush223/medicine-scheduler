package android.example.medicinescheduerapp.ui.home;

import android.content.Intent;
import android.example.medicinescheduerapp.MainActivity;
import android.example.medicinescheduerapp.ui.login.SignupFragment;
import android.example.medicinescheduerapp.ui.login.loginFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.example.medicinescheduerapp.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button loginBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        loginBtn=root.findViewById(R.id.home_to_login);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return root;
    }
}
