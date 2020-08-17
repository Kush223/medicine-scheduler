package android.example.medicinescheduerapp.ui.prescription;

import android.example.medicinescheduerapp.Prescription;
import android.example.medicinescheduerapp.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class prescriptionAdapter extends ListAdapter<Prescription,prescriptionAdapter.NoteHolder> {
    private ArrayList<Prescription> mlistItem;

    protected prescriptionAdapter(ArrayList<Prescription> listItem) {
        super(diffCallback);
        mlistItem=listItem;
    }

    private static final DiffUtil.ItemCallback<Prescription> diffCallback = new DiffUtil.ItemCallback<Prescription>() {

        @Override
        public boolean areItemsTheSame(@NonNull Prescription oldItem, @NonNull Prescription newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Prescription oldItem, @NonNull Prescription newItem) {
            return oldItem.getMed_name().equals(newItem.getMed_name())
                    && oldItem.getMed_days().equals(newItem.getMed_days())
                    && oldItem.getMed_dosage().equals(newItem.getMed_dosage())
                    && oldItem.getMed_extra_info().equals(newItem.getMed_extra_info());
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.med_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Prescription currentPres =mlistItem.get(position) ;
        holder.pres_med_name.setText(currentPres.getMed_name());
        holder.pres_med_days.setText(currentPres.getMed_days());
        holder.pres_med_dosage.setText(currentPres.getMed_dosage());
        holder.pres_med_extraInfo.setText(currentPres.getMed_extra_info());
    }

    public class NoteHolder extends RecyclerView.ViewHolder
    {
        private TextView pres_med_name;
        private TextView pres_med_days;
        private TextView pres_med_dosage;
        private TextView pres_med_extraInfo;
        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            pres_med_name=itemView.findViewById(R.id.pres_med_name);
            pres_med_days=itemView.findViewById(R.id.pres_med_days);
            pres_med_dosage=itemView.findViewById(R.id.pres_med_dosage);
            pres_med_extraInfo=itemView.findViewById(R.id.pres_med_extra);
        }
    }
}
