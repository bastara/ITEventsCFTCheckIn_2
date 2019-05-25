package com.example.iteventscftcheck_in;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
            itemBackground = itemView.findViewById(R.id.list_item);

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
        View view = inflater.inflate(R.layout.news_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
//        if (!cursor.moveToPosition(position)) {
//            return;
//        }



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String eventDateStart = null;
        try {
            eventDateStart = DateFormat.getDateInstance(DateFormat.MEDIUM)
                                       .format(sdf.parse(events.get(position)
                                                               .getDate()
                                                               .getStart()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String date = eventDateStart;

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

//        String city = events.get(position)
//                            .getTitle();
        String news = events.get(position)
                            .getTitle();
        String count = events.get(position)
                             .getEventFormat();
        String description = events.get(position)
                                   .getDescription();



        holder.cityText.setText(city);
        holder.dateText.setText(date);
        holder.nameText.setText(news);
        holder.countText.setText(count);
        holder.descriptionText.setText(description);
        holder.itemBackground.setBackgroundResource(R.drawable.ic_launcher_background);
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

//
//    public EventAdapter(List<Events> events) {
//        this.events = events;
//    }
//
//
//    public static class CardViewHolder extends RecyclerView.ViewHolder {
//        CardView cardView;
//
//        CardViewHolder(CardView cv) {
//            super(cv);
//            cardView = cv;
//        }
//    }
//
//    @Override
//    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
//                                               .inflate(R.layout.card_view_event, parent, false);
//        return new CardViewHolder(cv);
//    }
//
//    @Override
//    public void onBindViewHolder(CardViewHolder cardViewHolder, int position) {
//        CardView cardView = cardViewHolder.cardView;
//        TextView title = cardView.findViewById(R.id.title);
//        title.setText( events.get(position).getTitle());
//        TextView content = cardView.findViewById(R.id.content);
//        content.setText((CharSequence) events.get(position).getIconStatus());
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        if (events == null)
//            return 0;
//        return events.size();
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder {
//        TextView post;
//        TextView site;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            post = (TextView) itemView.findViewById(R.id.textTitle);
//            site = (TextView) itemView.findViewById(R.id.textDate);
//        }
//    }
//}
