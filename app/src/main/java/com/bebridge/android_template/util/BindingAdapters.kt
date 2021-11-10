package com.bebridge.android_template.util

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bebridge.android_template.R
import com.bebridge.android_template.adapter.FarmsRecyclerAdapter
import com.bebridge.android_template.api.response.ApiFarm
import com.bebridge.android_template.db.entity.ProductEntity
import com.bebridge.android_template.groupie.ImagePagerItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import jp.wasabeef.picasso.transformations.CropCircleTransformation

object BindingAdaptersForRecyclerView {

  @SuppressLint("NotifyDataSetChanged")
  @BindingAdapter("farm")
  @JvmStatic
  fun RecyclerView.setFarm(farms: ObservableField<List<ApiFarm>>) {

    adapter?.apply {
      this as FarmsRecyclerAdapter
      this.farms = farms.get()
      notifyDataSetChanged()
    }

  }

}

object BindingAdaptersForViewPage2 {

  @BindingAdapter(value = ["product"])
  @JvmStatic
  fun ViewPager2.setProduct(product: ProductEntity?) {

    val items = ArrayList<BindableItem<*>>()

    DebugManager.instance.log(this, "setProduct=$product")

    product?.photo1?.let {
      items.add(ImagePagerItem(it, this.adapter as GroupAdapter))
    }

    product?.photo2?.let {
      items.add(ImagePagerItem(it, this.adapter as GroupAdapter))
    }

    (this.adapter as GroupAdapter).update(items)

  }

}

object BindingAdapterForImageView {

  @BindingAdapter("profile_url")
  @JvmStatic
  fun ImageView.setProfileUrl(url: String?) {
    DebugManager.instance.log(this, "url=$url")
    url?.let {
      if (url.isNotEmpty()) {
        Picasso.get()
          .load(it)
          .resize(dpToPx(90), dpToPx(90))
          .onlyScaleDown()
          .centerCrop()
          .transform(CropCircleTransformation())
          .into(this, object : Callback {
            override fun onSuccess() {
              visibility = View.VISIBLE
            }

            override fun onError(e: Exception?) {
              e?.printStackTrace()
              visibility = View.VISIBLE
            }
          })
      } else {
        // if string is empty, it means the user not yet registered any profile picture
        visibility = View.VISIBLE
      }
    } ?: run {
      // when creating the screen, at first profile_url is set as null
      setImageDrawable(resources.getDrawable(R.drawable.user_default, null))
    }
  }


  fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
  }
}