package com.arif.helpnear;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.arif.helpnear.databinding.ActivityHelplinesBinding;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;
import com.arif.helpnear.Helpline;
import com.arif.helpnear.HelplineAdapter;

public class HelplinesActivity extends AppCompatActivity {

    private ActivityHelplinesBinding binding;
    private DatabaseReference databaseRef;
    private HelplineAdapter adapter;
    private List<Helpline> helplineList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelplinesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseRef = FirebaseDatabase.getInstance().getReference("helplines");

        binding.recyclerViewHelplines.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HelplineAdapter(helplineList);
        binding.recyclerViewHelplines.setAdapter(adapter);

        loadHelplines();
    }

    private void loadHelplines() {
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                helplineList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Helpline helpline = dataSnapshot.getValue(Helpline.class);
                    if (helpline != null) {
                        helplineList.add(helpline);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}
