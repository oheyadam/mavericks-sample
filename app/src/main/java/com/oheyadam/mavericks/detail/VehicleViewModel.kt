package com.oheyadam.mavericks.detail

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.oheyadam.mavericks.di.AssistedViewModelFactory
import com.oheyadam.mavericks.di.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class VehicleViewModel @AssistedInject constructor(
    @Assisted initialState: VehicleState,
    private val getVehicle: GetVehicle,
) : MavericksViewModel<VehicleState>(initialState) {

    init {
        loadVehicle(initialState.vehicleId)
    }

    private fun loadVehicle(vehicleId: String) {
        suspend {
            getVehicle(vehicleId)
        }.execute { copy(request = it, vehicleManufacturer = it()?.manufacturer.orEmpty()) }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<VehicleViewModel, VehicleState> {
        override fun create(state: VehicleState): VehicleViewModel
    }

    companion object : MavericksViewModelFactory<VehicleViewModel, VehicleState> by hiltMavericksViewModelFactory()
}
