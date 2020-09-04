package android.example.medicinescheduerapp.ui.docPrescriptions;

import android.example.medicinescheduerapp.Post;
import android.example.medicinescheduerapp.ui.prescription.Prescription;
import android.example.medicinescheduerapp.ui.prescription.PrescriptionPost;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.example.medicinescheduerapp.R;

import java.util.ArrayList;
import java.util.List;

public class DocPrescriptionsFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<PrescriptionPost> mlistview;
    private List<Prescription> medlist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_doc_prescriptions, container, false);
        mlistview= new ArrayList<>();
        medlist = new ArrayList<>();

        recyclerView = root.findViewById(R.id.recycler_view_doc_prescriptions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        medlist.add(new Prescription("Sccdv","dcdsv","dvs"));

        mlistview.add(new PrescriptionPost(null,"segs",20,"vsdvds",medlist));

        DocPresAdapter adapter = new DocPresAdapter(mlistview,getActivity());
        recyclerView.setAdapter(adapter);
        return root;
    }
}