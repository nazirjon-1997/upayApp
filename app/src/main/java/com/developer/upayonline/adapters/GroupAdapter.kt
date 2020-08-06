package com.developer.upayonline.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.upayonline.R
import com.developer.upayonline.models.GroupModel
import kotlinx.android.synthetic.main.item_group.view.*

class GroupAdapter(var items : ArrayList<GroupModel>, var clickListner: OnItemClickListner) : RecyclerView.Adapter<ViewHolder>(){
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        lateinit var viewHolder : RecyclerView.ViewHolder
        viewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent,false ))
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initialize(items.get(position),clickListner)
        val groupModel = items.get(position)
        if (groupModel.id == 1) {
            holder.iconGroup.setImageResource(R.drawable.mobilnaya_svyaz)
        } else if (groupModel.id == 2) {
            holder.iconGroup.setImageResource(R.drawable.ngn_domashniy)
        } else if (groupModel.id == 3) {
            holder.iconGroup.setImageResource(R.drawable.internet_provayderi)
        } else if (groupModel.id == 4) {
            holder.iconGroup.setImageResource(R.drawable.communalnie_uslugi)
        } else if (groupModel.id == 5) {
            holder.iconGroup.setImageResource(R.drawable.bankovskie_uslugi)
        } else if (groupModel.id == 6) {
            holder.iconGroup.setImageResource(R.drawable.soc_online_game)
        } else if (groupModel.id == 7) {
            holder.iconGroup.setImageResource(R.drawable.ewallet)
        } else if (groupModel.id == 8) {
            holder.iconGroup.setImageResource(R.drawable.other)
        }
    }
}

class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
    var groupName = itemView.name_group
    var iconGroup = itemView.icon_group
    fun initialize(item: GroupModel, action:OnItemClickListner){
        groupName.text = item.name
        itemView.setOnClickListener{
            action.onItemClick(item,adapterPosition)
        }
    }
}

interface OnItemClickListner{
    fun onItemClick(item: GroupModel, position: Int)
}