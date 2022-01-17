package com.oheyadam.mavericks.repository

import com.oheyadam.mavericks.repository.domain.Vehicle
import com.oheyadam.mavericks.repository.domain.vehicles
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor() {

    suspend fun login(username: String, password: String): Boolean {
        println("Username: $username. Password: $password")
        delay(2000)
        return true
    }

    suspend fun failLogin(): Boolean {
        delay(2000)
        throw NullPointerException()
    }

    suspend fun getVehicles(): List<Vehicle> {
        delay(3000)
        return vehicles
    }

    suspend fun getVehicle(vehicleId: String): Vehicle? {
        delay(1000)
        return vehicles.firstOrNull { it.id == vehicleId }
    }
}
