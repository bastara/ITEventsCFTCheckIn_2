package com.example.iteventscftcheck_in;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Events {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("cities")
    @Expose
    private List<City> cities = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("format")
    @Expose
    private Integer format;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("cardImage")
    @Expose
    private String cardImage;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("iconStatus")
    @Expose
    private String iconStatus;
    @SerializedName("eventFormat")
    @Expose
    private String eventFormat;
    @SerializedName("eventFormatEng")
    @Expose
    private String eventFormatEng;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIconStatus() {
        return iconStatus;
    }

    public void setIconStatus(String iconStatus) {
        this.iconStatus = iconStatus;
    }

    public String getEventFormat() {
        return eventFormat;
    }

    public void setEventFormat(String eventFormat) {
        this.eventFormat = eventFormat;
    }

    public String getEventFormatEng() {
        return eventFormatEng;
    }

    public void setEventFormatEng(String eventFormatEng) {
        this.eventFormatEng = eventFormatEng;
    }
}


//public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
//    private List<Events> events;
//    private final Context context;
//    private ItemClickListener itemClickListener;
//
//    EventAdapter(Context context, List<Events> events) {
//        this.events = events;
//        this.context = context;
//    }
//
//
//    class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        final TextView dateText;
//        final TextView cityText;
//        final TextView nameText;
//        final TextView countText;
//        final TextView descriptionText;
//        final View itemBackground;
//
//        EventViewHolder(View itemView) {
//            super(itemView);
//
//            cityText = itemView.findViewById(R.id.tv_city);
//            dateText = itemView.findViewById(R.id.tv_date);
//            nameText = itemView.findViewById(R.id.tv_name);
//            countText = itemView.findViewById(R.id.tv_count);
//            descriptionText = itemView.findViewById(R.id.tv_description);
//            itemBackground = itemView.findViewById(R.id.icon);
//
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//            if (itemClickListener != null) {
//                itemClickListener.onItemClick(getAdapterPosition());
//            }
//        }
//    }
//
//    @NonNull
//    @Override
//    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.item, parent, false);
//        return new EventViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
////        if (!cursor.moveToPosition(position)) {
////            return;
////        }
//
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
//        String eventDateStart = null;
//        try {
//            eventDateStart = DateFormat.getDateInstance(DateFormat.MEDIUM)
//                                       .format(sdf.parse(events.get(position)
//                                                               .getDate()
//                                                               .getStart()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        String date = eventDateStart;
//
//        List<City> listCity = events.get(position)
//                                    .getCities();
//
//        String city = "";
//
//        for (int i = 0; i < listCity.size(); i++) {
//            if (i != 0) {
//                city += "\n";
//            }
//            city += listCity.get(i)
//                            .getNameRus();
//        }
//
//
//        String news = events.get(position)
//                            .getTitle();
//        String count = events.get(position)
//                             .getEventFormat();
//        String description = events.get(position)
//                                   .getDescription();
//
//
//        ImageView imageView = (ImageView) holder.itemBackground;
//
//        Glide
//                .with(context)
//                .load("https://team.cft.ru" + events.get(position)
//                                                    .getCardImage())
//                .into(imageView);
//
//        holder.cityText.setText(city);
//        holder.dateText.setText(date);
//        holder.nameText.setText(news);
//        holder.countText.setText(count);
//        holder.descriptionText.setText(description);
//
////        holder.itemBackground.setBackgroundResource(R.drawable.qqqw2);
//    }
//
//    public void setClickListener(ItemClickListener itemClickListener) {
//        this.itemClickListener = itemClickListener;
//    }
//
//    public interface ItemClickListener {
//        void onItemClick(int position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return events.size();
//    }
