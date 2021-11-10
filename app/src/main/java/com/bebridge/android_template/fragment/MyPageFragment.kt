package com.bebridge.android_template.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bebridge.android_template.CustomApplication
import com.bebridge.android_template.api.response.ErrorResponse
import com.bebridge.android_template.databinding.FragmentMyPageBinding
import com.bebridge.android_template.viewModel.MyPageViewModel
import javax.inject.Inject

class MyPageFragment : Fragment(), MyPageNavigator {

  @Inject
  lateinit var viewModel: MyPageViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    (activity?.application as CustomApplication).getComponent().inject(this)

  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val binding = FragmentMyPageBinding.inflate(inflater, container, false)
    binding.viewModel = viewModel

    viewModel.onCreateView(this)

    return binding.root
  }

  override fun onDestroyView() {
    viewModel.onDestroyView()
    super.onDestroyView()
  }

  override fun onError(errorResponse: ErrorResponse) {
    Toast.makeText(context, errorResponse.messageString(requireContext()), Toast.LENGTH_SHORT)
      .show()
  }
}

interface MyPageNavigator {

  fun onError(errorResponse: ErrorResponse)
}