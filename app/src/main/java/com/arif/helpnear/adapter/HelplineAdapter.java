package com.arif.helpnear;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.arif.helpnear.Helpline;
import com.arif.helpnear.HelplineAdapter;

public class HelplineAdapter extends RecyclerView.Adapter<HelplineAdapter.ViewHolder> {

    private List<Helpline> helplineList;

    public HelplineAdapter(List<Helpline> helplineList) {
        this.helplineList = helplineList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_helpline, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Helpline helpline = helplineList.get(position);
        holder.title.setText(helpline.title);
        holder.number.setText(helpline.number);
    }

    @Override
    public int getItemCount() {
        return helplineList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, number;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            number = itemView.findViewById(R.id.textViewNumber);
        }
    }
}
