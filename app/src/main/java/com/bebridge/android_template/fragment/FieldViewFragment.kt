package com.bebridge.android_template.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bebridge.android_template.CustomApplication
import com.bebridge.android_template.databinding.FragmentFieldViewBinding
import com.bebridge.android_template.viewModel.FarmDetailsViewModel
import com.bebridge.android_template.viewModel.ViewPagerAdapter
import javax.inject.Inject

class FieldViewFragment : Fragment() {

  var binding: FragmentFieldViewBinding? = null

  @Inject
  lateinit var viewModel: FarmDetailsViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (activity?.application as CustomApplication).getComponent().inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentFieldViewBinding.inflate(inflater, container, false)
    binding?.viewModel = viewModel

    viewModel.initialize()

    binding?.viewpager?.let { setupViewPager(it) }
    binding?.slidingTabs?.setupWithViewPager(binding?.viewpager)

    binding?.backButton?.setOnClickListener {
      activity?.onBackPressed()
    }
    return binding?.root
  }

  private fun setupViewPager(viewpager: ViewPager) {
    val adapter = ViewPagerAdapter(childFragmentManager)

    adapter.addFragment(SoilFragment(), "Soil")
    adapter.addFragment(IrrigationFragment(), "Crops")
    adapter.addFragment(IrrigationFragment(), "Irrigation")
    adapter.addFragment(IrrigationFragment(), "Agronomy")
    adapter.addFragment(IrrigationFragment(), "Controller")

    viewpager.adapter = adapter
  }

}