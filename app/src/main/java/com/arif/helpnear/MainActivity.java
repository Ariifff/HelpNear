package com.arif.helpnear;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.arif.helpnear.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatabaseReference databaseRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReference("complaints");

        String[] issues = {"Select issue", "Water leakage", "Wire cut", "Street light not working", "Garbage dump", "Animals", "Others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, issues);
        binding.spinnerIssueType.setAdapter(adapter);

        binding.editTextExtraDetail.setVisibility(View.GONE);

        binding.buttonSubmit.setOnClickListener(v -> handleSubmit());

        binding.buttonViewComplaints.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ViewComplaintsActivity.class));
        });

        binding.buttonHelplines.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, HelplinesActivity.class));
        });

        binding.buttonLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });


    }

    private void handleSubmit() {
        String name = binding.editTextName.getText().toString().trim();
        String contact = binding.editTextContact.getText().toString().trim();
        String issue = binding.spinnerIssueType.getSelectedItem().toString();
        String location = binding.editTextLocation.getText().toString().trim();
        String description = binding.editTextDescription.getText().toString().trim();

        if (name.isEmpty()) showToast("Enter your name");
        else if (contact.isEmpty()) showToast("Enter contact number");
        else if (issue.equals("Select issue")) showToast("Select an issue type");
        else if (location.isEmpty()) showToast("Enter location");
        else {
            String id = databaseRef.push().getKey();
            Complaint complaint = new Complaint(id, name, contact, issue, location, description);
            databaseRef.child(id).setValue(complaint)
                    .addOnSuccessListener(aVoid -> {
                        showToast("Complaint submitted successfully!");
                        clearFields();
                    })
                    .addOnFailureListener(e -> showToast("Failed to submit: " + e.getMessage()));
        }
    }

    private void clearFields() {
        binding.editTextName.setText("");
        binding.editTextContact.setText("");
        binding.spinnerIssueType.setSelection(0);
        binding.editTextLocation.setText("");
        binding.editTextDescription.setText("");
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Do you really want to exit?")
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNegativeButton("No", null)
                .show();
    }


    public static class Complaint {
        public String id, name, contact, issue, location, description;

        public Complaint() {}

        public Complaint(String id, String name, String contact, String issue,
                         String location, String description) {
            this.id = id;
            this.name = name;
            this.contact = contact;
            this.issue = issue;
            this.location = location;
            this.description = description;
        }
    }
}
