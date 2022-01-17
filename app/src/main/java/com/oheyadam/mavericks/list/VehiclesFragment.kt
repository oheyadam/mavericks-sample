package com.oheyadam.mavericks.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.google.android.material.snackbar.Snackbar
import com.oheyadam.mavericks.R
import com.oheyadam.mavericks.databinding.VehiclesFragmentBinding
import com.oheyadam.mavericks.detail.VehicleFragment
import com.oheyadam.mavericks.list.UiEvent.GoToVehicle
import com.oheyadam.mavericks.list.UiEvent.ShowGolfSnackbar
import com.oheyadam.mavericks.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvents
                    .collect { event ->
                        when (event) {
                            is GoToVehicle -> showVehicleDetails(event)
                            ShowGolfSnackbar -> showSnackbar()
                        }
                    }
            }
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

    private fun showVehicleDetails(event: GoToVehicle) {
        findNavController().navigate(
            R.id.action_VehiclesFragment_to_VehicleFragment,
            VehicleFragment.args(event.vehicleId)
        )
    }

    private fun showSnackbar() {
        Snackbar.make(binding.root, getString(R.string.clicked_golf), Snackbar.LENGTH_LONG)
            .show()
    }
}
