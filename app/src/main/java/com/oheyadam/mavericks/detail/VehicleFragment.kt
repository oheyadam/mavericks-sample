package com.oheyadam.mavericks.detail

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.asMavericksArgs
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oheyadam.mavericks.databinding.VehicleFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize

@Parcelize
data class VehicleFragmentArgs(val vehicleId: String) : Parcelable

@AndroidEntryPoint
class VehicleFragment : BottomSheetDialogFragment(), MavericksView {

    private var binding: VehicleFragmentBinding? = null
    private val viewModel: VehicleViewModel by fragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun invalidate() = withState(viewModel) { state ->
        binding?.progress?.isVisible = state.isLoading
        binding?.manufacturer?.text = state.vehicleManufacturer
        binding?.model?.text = state.vehicleModel
    }

    companion object {
        fun args(vehicleId: String) = VehicleFragmentArgs(vehicleId).asMavericksArgs()
    }
}
