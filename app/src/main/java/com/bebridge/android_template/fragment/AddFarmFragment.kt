package com.bebridge.android_template.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bebridge.android_template.CustomApplication
import com.bebridge.android_template.databinding.FragmentAddFarmBinding
import com.bebridge.android_template.viewModel.AddFarmViewModel
import javax.inject.Inject

class AddFarmFragment : Fragment() {

  var binding: FragmentAddFarmBinding? = null

  @Inject
  lateinit var viewModel: AddFarmViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    (activity?.application as CustomApplication).getComponent().inject(this)

  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = FragmentAddFarmBinding.inflate(inflater, container, false)
    binding?.viewModel = viewModel

    binding?.backButton?.setOnClickListener {
      activity?.onBackPressed()
    }
    return binding?.root
  }

}