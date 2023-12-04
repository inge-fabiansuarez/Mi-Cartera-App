package com.fabiansuarez.micartera.model.entity

data class Account(
    var id: String = "",
    var name: String = "",
    var balance: Double = 0.0,
    var accountsTypeId: Int = 0,
    var userId: String = ""
)
