package com.oheyadam.mavericks.repository.domain

import java.util.UUID

const val GOLF_ID = "Golf"

val vehicles = listOf(
    Vehicle(id = GOLF_ID, model = "Golf", manufacturer = "Volkswagen"),
    Vehicle(model = "Passat", manufacturer = "Volkswagen"),
    Vehicle(model = "Polo", manufacturer = "Volkswagen"),
    Vehicle(model = "Tiguan", manufacturer = "Volkswagen"),
    Vehicle(model = "Taigo", manufacturer = "Volkswagen"),
    Vehicle(model = "Touran", manufacturer = "Volkswagen"),
    Vehicle(model = "Tourareg", manufacturer = "Volkswagen"),
    Vehicle(model = "T-Rog", manufacturer = "Volkswagen"),
    Vehicle(model = "Caddy", manufacturer = "Volkswagen"),
    Vehicle(model = "T-Cross", manufacturer = "Volkswagen"),
    Vehicle(model = "ID.4", manufacturer = "Volkswagen"),
    Vehicle(model = "ID.3", manufacturer = "Volkswagen"),
    Vehicle(model = "up!", manufacturer = "Volkswagen"),
    Vehicle(model = "Sharan", manufacturer = "Volkswagen"),
    Vehicle(model = "Arteon", manufacturer = "Volkswagen"),
    Vehicle(model = "ID.5", manufacturer = "Volkswagen"),
    Vehicle(model = "Cayenne", manufacturer = "Porsche"),
    Vehicle(model = "Macan", manufacturer = "Porsche"),
    Vehicle(model = "Panamera", manufacturer = "Porsche"),
    Vehicle(model = "Taycan", manufacturer = "Porsche")
)

data class Vehicle(
    val id: String = UUID.randomUUID().toString(),
    val model: String,
    val manufacturer: String
)
