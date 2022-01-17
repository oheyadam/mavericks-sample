package com.oheyadam.mavericks.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.google.android.material.snackbar.Snackbar
import com.oheyadam.mavericks.R
import com.oheyadam.mavericks.databinding.VehiclesFragmentBinding
import com.oheyadam.mavericks.detail.VehicleFragment
import com.oheyadam.mavericks.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehiclesFragment : Fragment(R.layout.vehicles_fragment), MavericksView {

    private val binding: VehiclesFragmentBinding by viewBinding()
    private val viewModel: VehiclesViewModel by fragmentViewModel()
    private val vehiclesAdapter: VehiclesAdapter by lazy { VehiclesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*viewModel.onEach(VehiclesState::vehicles) { vehicles ->
            vehiclesAdapter.submitList(vehicles)
        }*/
        viewModel.onEach(VehiclesState::selectedVehicleId, uniqueOnly()) { selectedVehicleId ->
            if (selectedVehicleId != null) showVehicleDetails(selectedVehicleId)
        }
        viewModel.onEach(VehiclesState::selectedGolfSnackbar, uniqueOnly()) { showSnackbar ->
            if (showSnackbar) showSnackbar()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            vehicles.layoutManager = LinearLayoutManager(requireContext())
            vehicles.adapter = vehiclesAdapter
            swipeRefresh.setOnRefreshListener {
                viewModel.fetchVehicles()
            }
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        with(binding) {
            swipeRefresh.isRefreshing = state.isLoading
            vehiclesAdapter.submitList(state.vehicles)
        }
    }

    private fun showVehicleDetails(vehicleId: String) {
        findNavController().navigate(
            R.id.action_VehiclesFragment_to_VehicleFragment,
            VehicleFragment.args(vehicleId)
        )
    }

    private fun showSnackbar() {
        Snackbar.make(binding.root, getString(R.string.clicked_golf), Snackbar.LENGTH_LONG)
            .show()
    }
}
