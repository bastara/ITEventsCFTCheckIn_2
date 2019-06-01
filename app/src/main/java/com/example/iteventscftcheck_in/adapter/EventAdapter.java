package com.example.iteventscftcheck_in.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.iteventscftcheck_in.R;
import com.example.iteventscftcheck_in.db.model.EventsModel;

import java.util.List;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private Context context;
    private ItemClickListener itemClickListener;
    private List<EventsModel> eventsModels;

    public EventAdapter(List<EventsModel> eventsModels) {
        this.eventsModels = eventsModels;
    }


    class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView dateText;
        final TextView cityText;
        final TextView nameText;
        final TextView countText;
        final TextView descriptionText;
        final View itemBackground;


        EventViewHolder(View itemView) {
            super(itemView);

            cityText = itemView.findViewById(R.id.tv_city);
            dateText = itemView.findViewById(R.id.tv_date);
            nameText = itemView.findViewById(R.id.tv_name);
            countText = itemView.findViewById(R.id.tv_count);
            descriptionText = itemView.findViewById(R.id.tv_decription);
            itemBackground = itemView.findViewById(R.id.icon);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.dateText.setText(eventsModels.get(position)
                                            .getDate());
        holder.cityText.setText(eventsModels.get(position)
                                            .getCity());
        holder.nameText.setText(eventsModels.get(position)
                                            .getName());
        holder.countText.setText(eventsModels.get(position)
                                             .getCount());
        holder.descriptionText.setText(eventsModels.get(position)
                                                   .getDescription());

        ImageView imageView = (ImageView) holder.itemBackground;

        Glide
                .with(context)
                .load(context.getString(R.string.url) + eventsModels.get(position)
                                                          .getUrlBackground())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);

    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return eventsModels.size();
    }
}
