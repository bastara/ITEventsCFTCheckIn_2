package com.example.iteventscftcheck_in;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder> {

    private List<Members> events;

    private final Context context;

    private ItemClickListener itemClickListener;

    public ParticipantAdapter(Context context, List<Members> events) {
        this.events = events;
        this.context = context;
    }

    public class ParticipantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView linkText;

        ParticipantViewHolder(View itemView) {
            super(itemView);

            linkText = itemView.findViewById(R.id.tv_participant);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }


    @NonNull
    @Override
    public ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.participant_item, parent, false);
        return new ParticipantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantAdapter.ParticipantViewHolder holder, int position) {
        String news = events.get(position)
                            .getLastName();
        holder.linkText.setText(news);
    }

    public void setClickListener(ParticipantAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
