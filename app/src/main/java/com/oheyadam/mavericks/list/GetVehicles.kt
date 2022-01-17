package com.oheyadam.mavericks.list

import com.oheyadam.mavericks.list.model.VehicleItem
import com.oheyadam.mavericks.list.model.toVehicleItems
import com.oheyadam.mavericks.repository.Repository
import javax.inject.Inject

class GetVehicles @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(
        onItemClick: (id: String) -> Unit
    ): List<VehicleItem> = repository.getVehicles().toVehicleItems(onItemClick)
}
