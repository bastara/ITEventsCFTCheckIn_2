package com.example.iteventscftcheck_in;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iteventscftcheck_in.db.model.EventsModel;
import com.example.iteventscftcheck_in.db.model.ParticipantModel;

import java.util.List;

class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder> {

    private List<Members> members;
    private final Context context;
    private ItemClickListener itemClickListener;
    private List<ParticipantModel> participantModels;

    public ParticipantAdapter(Context context, List<ParticipantModel> participantModels) {
//        this.members = members;
        this.context = context;
        this.participantModels = participantModels;
    }

    public class ParticipantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView lastName;
        final CheckBox checkBox;

        ParticipantViewHolder(View itemView) {
            super(itemView);

            lastName = itemView.findViewById(R.id.tv_participant);
            checkBox = itemView.findViewById(R.id.cb_participant);

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


        holder.lastName.setText(participantModels.get(position).getFirstName());
    }

    public void setClickListener(ParticipantAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
//        return members.size();
        return participantModels.size();
    }
}
