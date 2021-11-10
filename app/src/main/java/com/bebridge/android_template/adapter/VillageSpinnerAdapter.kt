package com.bebridge.android_template.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.bebridge.android_template.R
import com.bebridge.android_template.databinding.ItemVillageBinding
import com.bebridge.android_template.db.entity.VillageEntity

class VillageSpinnerAdapter(private val inflater: LayoutInflater) : BaseAdapter() {
    var allvillage: List<VillageEntity>? = null

    override fun getCount(): Int {
        return allvillage?.size ?: 0
    }

    override fun getItem(position: Int): VillageEntity? {
        return allvillage?.get(position)
    }


    override fun getItemId(position: Int): Long {
        return allvillage?.get(position)?.villageId?: 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemVillageBinding = convertView?.let {
            it.tag as ItemVillageBinding
        } ?: let {
            DataBindingUtil.inflate<ItemVillageBinding>(
                inflater,
                R.layout.item_village,
                parent, false
            ).also {
                it.root.tag = it
            }
        }

        binding.village = getItem(position)
        return binding.root
    }


}