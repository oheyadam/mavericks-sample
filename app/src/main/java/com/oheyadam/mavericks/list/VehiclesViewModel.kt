package com.oheyadam.mavericks.list

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.oheyadam.mavericks.di.AssistedViewModelFactory
import com.oheyadam.mavericks.di.hiltMavericksViewModelFactory
import com.oheyadam.mavericks.list.UiEvent.GoToVehicle
import com.oheyadam.mavericks.list.UiEvent.ShowGolfSnackbar
import com.oheyadam.mavericks.repository.domain.GOLF_ID
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class VehiclesViewModel @AssistedInject constructor(
    @Assisted initialState: VehiclesState,
    private val getVehicles: GetVehicles,
) : MavericksViewModel<VehiclesState>(initialState) {

    private val mutableUiEvents = MutableSharedFlow<UiEvent>()
    val uiEvents: SharedFlow<UiEvent> = mutableUiEvents

    init {
        fetchVehicles()
        onAsync(
            VehiclesState::request,
            onSuccess = {
                /** Do one shot type of actions like tracking **/
            },
            onFail = {
                /** Do one shot type of actions like tracking **/
            }
        )
    }

    // https://airbnb.io/mavericks/#/async?id=retaining-data-across-reloads-with-retainvalue
    fun fetchVehicles() {
        suspend {
            getVehicles { vehicleId -> onVehicleClicked(vehicleId) }
        }.execute(retainValue = VehiclesState::request) { copy(request = it) }
    }

    private fun onVehicleClicked(vehicleId: String) {
        viewModelScope.launch {
            val emission = if (vehicleId == GOLF_ID) ShowGolfSnackbar else GoToVehicle(vehicleId)
            mutableUiEvents.emit(emission)
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<VehiclesViewModel, VehiclesState> {
        override fun create(state: VehiclesState): VehiclesViewModel
    }

    companion object : MavericksViewModelFactory<VehiclesViewModel, VehiclesState> by hiltMavericksViewModelFactory()
}
