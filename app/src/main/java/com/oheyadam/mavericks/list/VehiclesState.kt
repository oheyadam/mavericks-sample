package com.oheyadam.mavericks.list

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.oheyadam.mavericks.list.model.VehicleItem

data class VehiclesState(
    val request: Async<List<VehicleItem>> = Uninitialized,
    val selectedVehicleId: String? = null,
    val selectedGolfSnackbar: Boolean = false
) : MavericksState {
    val vehicles: List<VehicleItem> = request() ?: emptyList()
    val isLoading: Boolean = request is Loading

    fun resetSelectedVehicleId(): VehiclesState {
        return copy(selectedVehicleId = null)
    }
}
