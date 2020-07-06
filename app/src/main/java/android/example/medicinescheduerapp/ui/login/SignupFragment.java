package android.example.medicinescheduerapp.ui.login;

import android.content.Context;
import android.example.medicinescheduerapp.R;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SignupFragment extends Fragment {

    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getContext()!=null)
        {
            this.context = getContext();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_signup,container,false);
        TextView alreadyUser =(TextView)root.findViewById(R.id.already_user);
        alreadyUser.setOnClickListener(v -> {
            if(getActivity()!=null) {
                Log.v("TAG","success");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_auth_container, new loginFragment())
                        .commit();
            }
        });
        return root;
    }
}
