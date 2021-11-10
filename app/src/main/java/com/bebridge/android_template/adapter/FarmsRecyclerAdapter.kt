package com.bebridge.android_template.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bebridge.android_template.api.response.ApiFarm
import com.bebridge.android_template.databinding.ItemFarmBinding

class FarmsRecyclerAdapter :
  RecyclerView.Adapter<FarmsRecyclerAdapter.BindingHolder>() {

  var farms: List<ApiFarm>? = null
  lateinit var listener: OnItemClickListener

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
    setOnItemClickListener(listener)
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding = ItemFarmBinding.inflate(layoutInflater, parent, false)
    return BindingHolder(binding)
  }

  override fun onBindViewHolder(holder: BindingHolder, position: Int) {
    val farmItem = farms?.get(position)


    holder.binding.farm = farmItem

    holder.binding.mainLayout.setOnClickListener {
      farmItem?.let { data -> listener.onMainListClick(it, data) }
    }
  }

  override fun getItemCount(): Int {
    return farms?.size ?: 0
  }

  interface OnItemClickListener {
    fun onMainListClick(view: View, data: ApiFarm)
  }

  fun setOnItemClickListener(listener: OnItemClickListener) {
    this.listener = listener
  }

  class BindingHolder(var binding: ItemFarmBinding) :
    RecyclerView.ViewHolder(binding.root)
}