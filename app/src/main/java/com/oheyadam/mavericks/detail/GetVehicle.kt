package com.oheyadam.mavericks.detail

import com.oheyadam.mavericks.repository.Repository
import com.oheyadam.mavericks.repository.domain.Vehicle
import javax.inject.Inject

class GetVehicle @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(vehicleId: String): Vehicle? {
        return repository.getVehicle(vehicleId)
    }
}
