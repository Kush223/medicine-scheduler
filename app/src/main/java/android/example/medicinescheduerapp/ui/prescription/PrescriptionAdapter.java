package android.example.medicinescheduerapp.ui.prescription;

import android.content.Context;
import android.example.medicinescheduerapp.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.PresHolder> {

    private List<Prescription> listitem;
    private Context context;

    public PrescriptionAdapter(List<Prescription> listitem, Context context) {
        this.listitem = listitem;
        this.context = context;
    }

    @NonNull
    @Override
    public PresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.med_item,parent,false);
        return new PresHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PresHolder holder, int position) {
        Prescription pres =listitem.get(position);

        holder.med_name.setText(pres.getMed_name());
        holder.med_dose.setText(pres.getMed_dosage());
        holder.med_days.setText(pres.getMed_days());

    }

    @Override
    public int getItemCount() {
        return listitem.size();
    }

    public class PresHolder extends RecyclerView.ViewHolder{
        public TextView med_name;
        public TextView med_days;
        public TextView med_dose;

        public PresHolder(@NonNull View itemView) {
            super(itemView);

            med_name =(TextView)itemView.findViewById(R.id.pres_med_name);
            med_days =(TextView)itemView.findViewById(R.id.pres_med_days);
            med_dose =(TextView)itemView.findViewById(R.id.pres_med_dosage);
        }
    }
}
