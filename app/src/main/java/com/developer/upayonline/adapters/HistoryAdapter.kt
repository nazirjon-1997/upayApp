package com.developer.upayonline.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.upayonline.R
import com.developer.upayonline.models.Transaction
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(var items : ArrayList<Transaction>, var clickListner: OnHistoryItemClickListner) : RecyclerView.Adapter<HistoryViewHolder>(){
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        lateinit var viewHolder : RecyclerView.ViewHolder
        viewHolder = HistoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent,false ))
        return viewHolder
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val context = holder.itemView.context

        holder.initialize(items.get(position),clickListner)
        val transaction: Transaction = items.get(position)
        var icon_name = transaction.getServiceid()
        holder.amount.text = transaction.getAmount().toString() + " TJS"
        holder.number.text = transaction.getAccount()
        holder.datetime.text = transaction.getDateTime()
        if (icon_name != 0) {
            holder.iconService.setImageResource(context.getResources().getIdentifier("i$icon_name", "drawable", context.getPackageName())
            )
        } else {
            holder.iconService.setImageResource(R.drawable.noimage)
        }
    }
}

class HistoryViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
    var serviceName = itemView.service_name
    var iconService = itemView.service_icon
    var number = itemView.service_number
    var amount = itemView.amountView
    var datetime = itemView.data_vrem
    fun initialize(item: Transaction, action: OnHistoryItemClickListner){
        serviceName.text = item.getServiceName()
        itemView.setOnClickListener{
            action.onItemClick(item,adapterPosition)
        }
    }
}

interface OnHistoryItemClickListner{
    fun onItemClick(item: Transaction, position: Int)
}