package com.bebridge.android_template.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bebridge.android_template.CustomApplication
import com.bebridge.android_template.R
import com.bebridge.android_template.adapter.FarmsRecyclerAdapter
import com.bebridge.android_template.api.response.ApiFarm
import com.bebridge.android_template.api.response.ErrorResponse
import com.bebridge.android_template.databinding.FragmentHomeBinding
import com.bebridge.android_template.util.PreferenceController
import com.bebridge.android_template.viewModel.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import javax.inject.Inject

class HomeFragment : Fragment(), HomeNavigator {

  var binding: FragmentHomeBinding? = null

  @Inject
  lateinit var viewModel: HomeViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (activity?.application as CustomApplication).getComponent().inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = FragmentHomeBinding.inflate(inflater, container, false)
    binding?.viewModel = viewModel
    viewModel.initialize(this)

    binding?.buttonAddFarm?.setOnClickListener {
      val action = HomeFragmentDirections.actionHomeFragmentToAddFarmFragment()
      findNavController().navigate(action)
    }

    binding?.addFarm?.setOnClickListener {
      val action = HomeFragmentDirections.actionHomeFragmentToAddFarmFragment()
      findNavController().navigate(action)
    }

    binding?.openNotification?.setOnClickListener {
      val action = HomeFragmentDirections.actionHomeFragmentToNotificationFragment()
      findNavController().navigate(action)
    }

    showData()

    binding?.openMenu?.setOnClickListener {

      val dialog = context?.let { it1 -> BottomSheetDialog(it1) }
      val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)

      val btnClose = view.findViewById<ImageView>(R.id.btnDismiss)

      btnClose.setOnClickListener {
        dialog?.dismiss()
      }

      dialog?.setCancelable(true)

      dialog?.setContentView(view)

      dialog?.show()
    }

    return binding?.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    if (!PreferenceController.instance.getIsOnBoardingDone() && (PreferenceController.instance.getAccessToken() == null)) {
      val action = HomeFragmentDirections.actionHomeFragmentToOnBoardingFragment()
      findNavController().navigate(action)
    } else if (!PreferenceController.instance.getIsUserDataDone()) {
      val action = HomeFragmentDirections.actionHomeFragmentToSignUpFragment()
      findNavController().navigate(action)
    }
  }

  private fun showData() {
    binding?.farmsRecyclerview?.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = FarmsRecyclerAdapter()

      (adapter as FarmsRecyclerAdapter).setOnItemClickListener(object :
        FarmsRecyclerAdapter.OnItemClickListener {

        override fun onMainListClick(view: View, data: ApiFarm) {
          val action =
            HomeFragmentDirections.actionHomeFragmentToFarmDetailsFragment(data.farmId)
          findNavController().navigate(action)
        }

      })
    }

  }

  override fun onDestroyView() {
    binding = null
    super.onDestroyView()
  }

  override fun onRefreshed() {
  }

  override fun onError(errorResponse: ErrorResponse) {
    context?.let {
      Toast.makeText(it, errorResponse.messageString(it), Toast.LENGTH_SHORT).show()
    }
  }

}

interface HomeNavigator {
  fun onRefreshed()
  fun onError(errorResponse: ErrorResponse)
}
