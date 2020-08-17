package android.example.medicinescheduerapp.ui.prescription;

import android.content.Context;
import android.example.medicinescheduerapp.Post;
import android.example.medicinescheduerapp.Prescription;
import android.example.medicinescheduerapp.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class patientPrescribeAdapter extends ListAdapter<Prescription,patientPrescribeAdapter.NoteHolder> {


    protected patientPrescribeAdapter() {
        super(diffCallback);
    }

    private static final DiffUtil.ItemCallback<Prescription> diffCallback = new DiffUtil.ItemCallback<Prescription>() {

        @Override
        public boolean areItemsTheSame(@NonNull Prescription oldItem, @NonNull Prescription newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Prescription oldItem, @NonNull Prescription newItem) {
            return  oldItem.getPatient_weight().equals(newItem.getPatient_weight())
                    && oldItem.getPatient_symptoms().equals(newItem.getPatient_symptoms());
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prescription_item, parent, false);
        return new NoteHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Prescription currentPrescription = getItem(position);
//        holder.pres_item_date.setText(currentPrescription.getTitle());
        holder.pres_item_weight.setText(currentPrescription.getPatient_weight());
        holder.pres_item_symptoms.setText(String.valueOf(currentPrescription.getPatient_symptoms()));
    }

    public class NoteHolder extends RecyclerView.ViewHolder
    {
        private TextView pres_item_weight;
        private TextView pres_item_date;
        private TextView pres_item_symptoms;
        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            pres_item_date=itemView.findViewById(R.id.pres_item_date);
            pres_item_weight=itemView.findViewById(R.id.pres_item_weight);
            pres_item_symptoms=itemView.findViewById(R.id.pres_item_symptoms);
        }
    }
}
