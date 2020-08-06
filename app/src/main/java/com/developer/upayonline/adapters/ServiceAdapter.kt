package com.developer.upayonline.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.upayonline.R
import com.developer.upayonline.models.ServiceModel
import kotlinx.android.synthetic.main.item_sevice.view.*


class ServiceAdapter(var items : ArrayList<ServiceModel>, var clickListner: OnServiceItemClickListner) : RecyclerView.Adapter<ServiceViewHolder>(){
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        lateinit var viewHolder : RecyclerView.ViewHolder
        viewHolder = ServiceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sevice, parent,false ))
        return viewHolder
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val context = holder.itemView.context

        holder.initialize(items.get(position),clickListner)
        val serviceModel = items.get(position)
        var icon_name = serviceModel.serviceId
        if (icon_name != 0) {
            holder.iconService.setImageResource(context.getResources().getIdentifier("i$icon_name", "drawable", context.getPackageName())
            )
        } else {
            holder.iconService.setImageResource(R.drawable.noimage)
        }
    }
}

class ServiceViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
    var serviceName = itemView.name_service
    var iconService = itemView.icon_service
    fun initialize(item: ServiceModel, action: OnServiceItemClickListner){
        serviceName.text = item.serviceName
        itemView.setOnClickListener{
            action.onItemClick(item,adapterPosition)
        }
    }
}

interface OnServiceItemClickListner{
    fun onItemClick(item: ServiceModel, position: Int)
}