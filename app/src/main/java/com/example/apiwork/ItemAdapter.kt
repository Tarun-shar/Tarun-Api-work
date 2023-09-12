package com.example.apiwork

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apiwork.databinding.ItemFileBinding


class ItemAdapter(var dataList: ArrayList<ModelClass> , var context:Context) : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

//    private var onClickListener:OnClickListener ?= null
    class ViewHolder(var binding:ItemFileBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = ItemFileBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val module = dataList.get(position)
        holder.binding.apiTitle.text = dataList.get(position).title
        holder.binding.apiUser.text = dataList.get(position).user
        Glide.with(context).load(module.profile).into(holder.binding.apiImage)

//        holder.itemView.setOnClickListener {
//
//            if (onClickListener != null) {
//                onClickListener!!.onClick(position,module)
//            }
//        }

    }

//    // A function to bind the onclickListener.
//    fun setOnClickListener(onClickListener: OnClickListener) {
//        this.onClickListener = onClickListener
//    }
//
//    // onClickListener Interface
//    interface OnClickListener {
//        fun onClick(position: Int, model: ModelClass)
//    }
}