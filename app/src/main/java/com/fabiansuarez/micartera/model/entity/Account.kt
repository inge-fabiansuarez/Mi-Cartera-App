package com.fabiansuarez.micartera.model.entity


import com.fabiansuarez.micartera.TypeAccount
import com.google.firebase.firestore.PropertyName

data class Account(
    var id: String = "",
    var name: String = "",
    var balance: Double = 0.0,
    var accountsTypeId: TypeAccount = TypeAccount.CASH,
    var userId: String = ""
)
