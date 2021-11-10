package com.bebridge.android_template.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bebridge.android_template.CustomApplication
import com.bebridge.android_template.databinding.FragmentNotificationBinding
import com.bebridge.android_template.viewModel.NotificationViewModel
import javax.inject.Inject

class NotificationFragment : Fragment() {

  var binding: FragmentNotificationBinding? = null

  @Inject
  lateinit var viewModel: NotificationViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (activity?.application as CustomApplication).getComponent().inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = FragmentNotificationBinding.inflate(inflater, container, false)
    binding?.viewModel = viewModel

    binding?.backButton?.setOnClickListener {
      activity?.onBackPressed()
    }

    return binding?.root
  }

}