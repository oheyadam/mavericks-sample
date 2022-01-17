package com.oheyadam.mavericks.list

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.oheyadam.mavericks.list.model.VehicleItem

data class VehiclesState(
    val request: Async<List<VehicleItem>> = Uninitialized,
) : MavericksState {
    val vehicles: List<VehicleItem> = request() ?: emptyList()
    val isLoading: Boolean = request is Loading
}

sealed class UiEvent {
    data class GoToVehicle(val vehicleId: String) : UiEvent()
    object ShowGolfSnackbar : UiEvent()
}
