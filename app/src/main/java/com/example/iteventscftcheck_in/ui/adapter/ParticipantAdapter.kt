package com.example.iteventscftcheck_in.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.iteventscftcheck_in.App
import com.example.iteventscftcheck_in.R
import com.example.iteventscftcheck_in.db.model.ParticipantModel

class ParticipantAdapter(private val participantModels: List<ParticipantModel>) : RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder>() {

    private var context: Context? = null
    private var itemClickListener: ItemClickListener? = null
    private var participantModelEntity: ParticipantModel? = null
    private val databaseHelper = App.instance?.databaseInstance

    inner class ParticipantViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal val lastName: TextView = itemView.findViewById(R.id.tv_participant)
        internal val checkBox: CheckBox = itemView.findViewById(R.id.cb_participant)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.participant_item, parent, false)
        return ParticipantViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        holder.lastName.text = participantModels[position]
                .lastName

        holder.checkBox.setOnCheckedChangeListener(null)

        holder.checkBox.isChecked = participantModels[position]
                .isVisited

        holder.checkBox.setOnCheckedChangeListener { _, _ ->
            participantModelEntity = participantModels[holder.adapterPosition]

            if (participantModels[holder.adapterPosition]
                            .isVisited) {
                participantModelEntity!!.isVisited = false
            } else {
                participantModelEntity!!.isVisited = true
            }
            databaseHelper?.dataDao?.update(participantModelEntity!!)
        }
    }

    fun setClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun getItemCount(): Int {
        return participantModels.size
    }
}
