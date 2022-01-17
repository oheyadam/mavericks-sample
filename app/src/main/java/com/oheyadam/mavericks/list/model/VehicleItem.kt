package com.oheyadam.mavericks.list.model

import com.oheyadam.mavericks.repository.domain.Vehicle

data class VehicleItem(
    val id: String,
    val model: String,
    val manufacturer: String,
    val onItemClick: (id: String) -> Unit
)

fun Vehicle.toVehicleItem(
    onItemClick: (id: String) -> Unit
): VehicleItem = VehicleItem(id, model, manufacturer, onItemClick)

fun List<Vehicle>.toVehicleItems(
    onItemClick: (id: String) -> Unit
): List<VehicleItem> = map { vehicle -> vehicle.toVehicleItem(onItemClick) }
