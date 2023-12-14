package com.fabiansuarez.micartera.model.entity


import com.fabiansuarez.micartera.model.enums.TypeAccount
import com.google.firebase.firestore.Exclude

data class Account(
    @Exclude var id: String = "",
    var name: String = "",
    var balance: Double = 0.0,
    var accountsTypeId: TypeAccount = TypeAccount.CASH,
    var userId: String = ""
) {
}
