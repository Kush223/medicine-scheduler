package android.example.medicinescheduerapp.ui.docPrescriptions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.example.medicinescheduerapp.Post;
import android.example.medicinescheduerapp.R;
import android.example.medicinescheduerapp.ui.prescription.PrescriptionAdapter;
import android.example.medicinescheduerapp.ui.prescription.PrescriptionPost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DocPresAdapter extends RecyclerView.Adapter<DocPresAdapter.DocPresHolder> {
private List<PrescriptionPost> listitem;
private Context context;
private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
private OnItemClickListener listener;

public DocPresAdapter(List<PrescriptionPost> listitem, Context context) {
        this.listitem = listitem;
        this.context = context;
        }

@NonNull
@Override
public DocPresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_item,parent,false);
        return new DocPresHolder(v);
        }


@Override
public void onBindViewHolder(@NonNull DocPresHolder holder, int position) {
        PrescriptionPost prescriptionPost = listitem.get(position);

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.med.getContext(),LinearLayoutManager.VERTICAL,false);
        layoutManager.setInitialPrefetchItemCount(prescriptionPost.getPres().size());

        holder.date.setText(prescriptionPost.getDate());
        holder.pat_weight.setText( String.valueOf(prescriptionPost.getWeight()));
        holder.pat_symptoms.setText(prescriptionPost.getSymptoms());

        PrescriptionAdapter medAdapter = new PrescriptionAdapter(prescriptionPost.getPres(),context);
        holder.med.setLayoutManager(layoutManager);
        holder.med.setAdapter(medAdapter);
        holder.med.setRecycledViewPool(viewPool);
        }

@Override
public int getItemCount() {
        return listitem.size();
        }

public class DocPresHolder extends RecyclerView.ViewHolder {
    private TextView pat_weight;
    private TextView pat_symptoms;
    private TextView date;
    private RecyclerView med;
    private Button deletePres;

    public DocPresHolder(@NonNull View itemView) {
        super(itemView);
        date= (TextView) itemView.findViewById(R.id.pres_item_date);
        pat_weight=(TextView) itemView.findViewById(R.id.pres_item_weight);
        pat_symptoms=(TextView) itemView.findViewById(R.id.pres_item_med_symptoms);
        med=(RecyclerView) itemView.findViewById(R.id.recycler_view_pres_medicines);
        deletePres=itemView.findViewById(R.id.pres_delete);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(listitem.get(position));
                }
            }
        });
            deletePres.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(listitem.get(position),position);
                    }
                }
            });
    }
}
    public PrescriptionPost getItemAt(int position){
        return listitem.get(position);
    }

public interface OnItemClickListener {
    void onItemClick(PrescriptionPost prescriptionPost);
    void onDeleteClick(PrescriptionPost prescriptionPost, int position);
}

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
