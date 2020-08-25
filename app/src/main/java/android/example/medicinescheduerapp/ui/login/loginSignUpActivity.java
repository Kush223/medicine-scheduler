package android.example.medicinescheduerapp.ui.login;

import android.example.medicinescheduerapp.R;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class loginSignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        getWindow().setBackgroundDrawableResource(R.drawable.med_sched);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_auth_container,new loginFragment())
                .commit();
    }
}
