package com.example.iteventscftcheck_in.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.iteventscftcheck_in.App
import com.example.iteventscftcheck_in.R
import com.example.iteventscftcheck_in.db.model.EventsModel


internal class EventAdapter(private val eventsModels: List<EventsModel>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    private var context: Context? = null
    private var itemClickListener: ItemClickListener? = null

    private val count: String
        get() {
            val databaseHelper = App.instance?.databaseInstance
            val members = databaseHelper?.dataDao?.allDataParticipant?.size
            var participant = 0

            for (i in 0 until members!!) {
                val participantModel = databaseHelper.dataDao
                        .allDataParticipant[i]
                if (participantModel.isVisited) {
                    participant++
                }
            }
            return "$members/$participant"
        }

    internal inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val dateText: TextView = itemView.findViewById(R.id.tv_date)
        val cityText: TextView = itemView.findViewById(R.id.tv_city)
        val nameText: TextView = itemView.findViewById(R.id.tv_name)
        val countText: TextView = itemView.findViewById(R.id.tv_count)
        val descriptionText: TextView = itemView.findViewById(R.id.tv_description)
        val itemBackground: View = itemView.findViewById(R.id.icon)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (itemClickListener != null) {
                itemClickListener!!.onItemClick(adapterPosition)
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.event_item, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.dateText.text = eventsModels[position]
                .date
        holder.cityText.text = eventsModels[position]
                .city
        holder.nameText.text = eventsModels[position]
                .name
        holder.descriptionText.text = eventsModels[position]
                .description
        holder.countText.text = count

        val imageView = holder.itemBackground as ImageView

        Glide
                .with(context)
                .load(context!!.getString(R.string.url) + eventsModels[position]
                        .urlBackground!!)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView)

    }

    fun setClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun getItemCount(): Int {
        return eventsModels.size
    }
}
