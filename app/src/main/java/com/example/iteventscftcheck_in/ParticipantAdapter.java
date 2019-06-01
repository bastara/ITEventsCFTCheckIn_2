package com.example.iteventscftcheck_in;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iteventscftcheck_in.db.DatabaseHelper;
import com.example.iteventscftcheck_in.db.model.EventsModel;
import com.example.iteventscftcheck_in.db.model.ParticipantModel;

import java.util.List;

class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder> {

    private final Context context;
    private ItemClickListener itemClickListener;
    private List<ParticipantModel> participantModels;

    private ParticipantModel participantModelEntity;

    DatabaseHelper databaseHelper = App.getInstance()
                                       .getDatabaseInstance();


    public ParticipantAdapter(Context context, List<ParticipantModel> participantModels) {
        this.context = context;
        this.participantModels = participantModels;
    }

    public class ParticipantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
//            , CompoundButton.OnCheckedChangeListener
    {
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
    public void onBindViewHolder(@NonNull final ParticipantAdapter.ParticipantViewHolder holder, final int position) {
        holder.lastName.setText(participantModels.get(position)
                                                 .getLastName());

        holder.checkBox.setOnCheckedChangeListener(null);

        holder.checkBox.setChecked(participantModels.get(position)
                                                    .isVisited());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                participantModelEntity = participantModels.get(holder.getAdapterPosition());

                if (participantModels.get(holder.getAdapterPosition())
                                     .isVisited()) {
//                    participantModels.get(holder.getAdapterPosition())
//                                     .setVisited(false);
                    participantModelEntity.setVisited(false);
                } else {
//                    participantModels.get(holder.getAdapterPosition()).setVisited(true);
                    participantModelEntity.setVisited(true);
                }
                databaseHelper.getDataDao()
                              .update(participantModelEntity);
            }
        });
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
