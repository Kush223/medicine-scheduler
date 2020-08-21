package android.example.medicinescheduerapp.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.example.medicinescheduerapp.R;
import android.view.LayoutInflater;

import java.util.zip.Inflater;

public class LoadDialog {

    private Activity activity;
    private AlertDialog alertDialog;
    public LoadDialog(Activity myactivity){
        activity=myactivity;
    }
    public void startLoad(){
        AlertDialog.Builder builder =new AlertDialog.Builder(activity);

        LayoutInflater inflater =activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog,null));
        builder.setCancelable(true);

        alertDialog =builder.create();
        alertDialog.show();
    }
    public void dismissLoad(){
        alertDialog.dismiss();
    }

}
