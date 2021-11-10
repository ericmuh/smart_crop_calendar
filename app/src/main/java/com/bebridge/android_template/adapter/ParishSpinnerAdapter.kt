package com.bebridge.android_template.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.bebridge.android_template.R
import com.bebridge.android_template.databinding.ItemParishBinding
import com.bebridge.android_template.db.entity.ParishEntity

class ParishSpinnerAdapter(private val inflater: LayoutInflater) : BaseAdapter() {
  var parish: List<ParishEntity>? = null

  override fun getCount(): Int {
    return parish?.size ?: 0
  }

  override fun getItem(position: Int): ParishEntity? {
    return parish?.get(position)
  }

  override fun getItemId(position: Int): Long {
    return parish?.get(position)?.parishId ?: 0
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val binding: ItemParishBinding = convertView?.let {
      it.tag as ItemParishBinding
    } ?: let {
      DataBindingUtil.inflate<ItemParishBinding>(
        inflater,
        R.layout.item_parish,
        parent, false
      ).also {
        it.root.tag = it
      }
    }

    binding.parish = getItem(position)
    return binding.root
  }

}