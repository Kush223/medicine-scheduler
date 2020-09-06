package android.example.medicinescheduerapp.ui.docPrescriptions;

import android.content.Context;
import android.example.medicinescheduerapp.Post;
import android.example.medicinescheduerapp.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AllPatientsAdapter extends RecyclerView.Adapter<AllPatientsAdapter.AllPatientsHolder> {
    private List<Post> listitem;
    private Context context;
    private OnItemClickListener listener;

    public AllPatientsAdapter(List<Post> listitem, Context context) {
        this.listitem = listitem;
        this.context = context;
    }

    @NonNull
    @Override
    public AllPatientsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pat_item,parent,false);
        return new AllPatientsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllPatientsHolder holder, int position) {
        Post pat =listitem.get(position);

        holder.pat_name.setText(pat.getName());
        //holder.pat_age.setText();
        holder.pat_email.setText(pat.getEmail());
        holder.pat_number.setText(pat.getPhone());

    }

    @Override
    public int getItemCount() {
        return listitem.size();
    }

    public class AllPatientsHolder extends RecyclerView.ViewHolder{
        private TextView pat_name;
        private TextView pat_age;
        private TextView pat_email;
        private TextView pat_number;

        public AllPatientsHolder(@NonNull View itemView) {
            super(itemView);
            pat_name=(TextView) itemView.findViewById(R.id.all_pat_name);
            pat_age=(TextView) itemView.findViewById(R.id.all_pat_age);
            pat_email=(TextView) itemView.findViewById(R.id.all_pat_email);
            pat_number=(TextView) itemView.findViewById(R.id.all_pat_number);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(listitem.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Post post);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
