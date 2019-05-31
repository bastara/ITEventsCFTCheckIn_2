package com.example.iteventscftcheck_in;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Events> events;
    private final Context context;
    private ItemClickListener itemClickListener;

    EventAdapter(Context context, List<Events> events) {
        this.events = events;
        this.context = context;
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

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String date = null;
        try {
            date = DateFormat.getDateInstance(DateFormat.MEDIUM)
                             .format(sdf.parse(events.get(position)
                                                               .getDate()
                                                               .getStart()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<City> listCity = events.get(position)
                                    .getCities();

        String city = "";

        for (int i = 0; i < listCity.size(); i++) {
            if (i != 0) {
                city += "\n";
            }
            city += listCity.get(i)
                            .getNameRus();
        }


        String name = events.get(position)
                            .getTitle();
        String count = events.get(position)
                             .getEventFormat();
        String description = events.get(position)
                                   .getDescription();


        ImageView imageView = (ImageView) holder.itemBackground;

        Glide
                .with(context)
                .load("https://team.cft.ru" + events.get(position)
                                                    .getCardImage())
                .into(imageView);

        holder.cityText.setText(city);
        holder.dateText.setText(date);
        holder.nameText.setText(name);
        holder.countText.setText(count);
        holder.descriptionText.setText(description);

    }

    public void setClickListener(ItemClickListener itemClickListener) {
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