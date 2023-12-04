package com.fabiansuarez.micartera.model.entity

data class Movement(
    var id: String = "", // Cambiado a String para usar con Firebase
    var accountId: String = "", // Cambiado a String para usar con Firebase
    var typeMovement: Int = 0,
    var balance: String = "",
    var movementCategoryId: Int = 0,
    var userId: String = "" // ID del usuario al que pertenece este movimiento
)
