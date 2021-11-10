package com.bebridge.android_template.viewModel

import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(supportFragmentManager: FragmentManager) :
  FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  private var fragmentList1: ArrayList<Fragment> = ArrayList()
  private var fragmentTitleList1: ArrayList<String> = ArrayList()

  // returns which item is selected from arraylist of fragments.
  override fun getItem(position: Int): Fragment {
    return fragmentList1[position]
  }

  // returns which item is selected from arraylist of titles.
  @Nullable
  override fun getPageTitle(position: Int): CharSequence {
    return fragmentTitleList1[position]
  }

  // returns the number of items present in arraylist.
  override fun getCount(): Int {
    return fragmentList1.size
  }

  // this function adds the fragment and title in 2 separate  arraylist.
  fun addFragment(fragment: Fragment, title: String) {
    fragmentList1.add(fragment)
    fragmentTitleList1.add(title)
  }
}