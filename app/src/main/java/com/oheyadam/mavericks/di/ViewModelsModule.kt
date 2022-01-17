package com.oheyadam.mavericks.di

import com.oheyadam.mavericks.detail.VehicleViewModel
import com.oheyadam.mavericks.list.VehiclesViewModel
import com.oheyadam.mavericks.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun loginViewModelFactory(factory: LoginViewModel.Factory): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(VehiclesViewModel::class)
    fun vehiclesViewModelFactory(factory: VehiclesViewModel.Factory): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(VehicleViewModel::class)
    fun vehicleViewModelFactory(factory: VehicleViewModel.Factory): AssistedViewModelFactory<*, *>
}
