package com.arif.helpnear;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.arif.helpnear.databinding.ActivityViewComplaintsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewComplaintsActivity extends AppCompatActivity {

    private ActivityViewComplaintsBinding binding;
    private DatabaseReference databaseRef;
    private com.arif.helpnear.ComplaintAdapter adapter;
    private ArrayList<MainActivity.Complaint> complaintList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewComplaintsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseRef = FirebaseDatabase.getInstance().getReference("complaints");
        complaintList = new ArrayList<>();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new com.arif.helpnear.ComplaintAdapter(complaintList);
        binding.recyclerView.setAdapter(adapter);

        loadComplaints();
    }

    private void loadComplaints() {
        binding.progressBar.setVisibility(View.VISIBLE);
        databaseRef.get().addOnCompleteListener(task -> {
            binding.progressBar.setVisibility(View.GONE);
            if (task.isSuccessful()) {
                complaintList.clear();
                for (DataSnapshot snapshot : task.getResult().getChildren()) {
                    MainActivity.Complaint complaint = snapshot.getValue(MainActivity.Complaint.class);
                    complaintList.add(complaint);
                }
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Failed to load complaints: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
