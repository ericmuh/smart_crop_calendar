package com.bebridge.android_template.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bebridge.android_template.CustomApplication
import com.bebridge.android_template.databinding.FragmentFarmDetailsBinding
import com.bebridge.android_template.viewModel.FarmDetailsViewModel
import javax.inject.Inject

class FarmDetailsFragment : Fragment() {

  var binding: FragmentFarmDetailsBinding? = null

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
    binding = FragmentFarmDetailsBinding.inflate(inflater, container, false)
    binding?.viewModel = viewModel

    viewModel.initialize()

    binding?.backButton?.setOnClickListener {
      activity?.onBackPressed()
    }
    return binding?.root
  }

}