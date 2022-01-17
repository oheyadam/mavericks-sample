package com.oheyadam.mavericks.detail

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.oheyadam.mavericks.repository.domain.Vehicle

data class VehicleState(
    val vehicleId: String,
    val vehicleManufacturer: String = "",
    val request: Async<Vehicle?> = Uninitialized,
    val anotherRequest: Async<Vehicle?> = Uninitialized
) : MavericksState {
    constructor(args: VehicleFragmentArgs) : this(args.vehicleId)

    val isLoading: Boolean = request is Loading || anotherRequest is Loading
    val hasError: Boolean = !isLoading && (request is Fail || anotherRequest is Fail)
    val vehicleModel: String = request()?.model.orEmpty()
}
