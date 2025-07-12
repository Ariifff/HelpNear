package com.arif.helpnear;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.arif.helpnear.databinding.ItemComplaintBinding;

import java.util.List;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {

    private List<MainActivity.Complaint> complaintList;

    public ComplaintAdapter(List<MainActivity.Complaint> complaintList) {
        this.complaintList = complaintList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemComplaintBinding binding;

        public ViewHolder(ItemComplaintBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MainActivity.Complaint complaint) {
            binding.textViewName.setText("Name: " + complaint.name);
            binding.textViewContact.setText("Contact: " + complaint.contact);
            binding.textViewIssue.setText("Issue: " + complaint.issue);
            binding.textViewLocation.setText("Location: " + complaint.location);
            binding.textViewDescription.setText("Description: " + complaint.description);
        }
    }

    @NonNull
    @Override
    public ComplaintAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemComplaintBinding binding = ItemComplaintBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintAdapter.ViewHolder holder, int position) {
        holder.bind(complaintList.get(position));
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }
}
