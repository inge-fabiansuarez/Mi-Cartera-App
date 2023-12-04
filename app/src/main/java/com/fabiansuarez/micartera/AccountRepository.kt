package com.fabiansuarez.micartera

import androidx.lifecycle.MutableLiveData
import com.fabiansuarez.micartera.model.entity.Account
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AccountRepository {
    private val ACCOUNT_COLLECTION: String = "accounts"
    var account: MutableLiveData<Account> = MutableLiveData()
    var accounts: MutableLiveData<List<Account>> = MutableLiveData()

    private val firestore: FirebaseFirestore = Firebase.firestore

    fun loadAllFirestore() {
        firestore.collection(ACCOUNT_COLLECTION).get().addOnSuccessListener { result ->
            val accountList: ArrayList<Account> = arrayListOf()
            if (!result.isEmpty) {
                for (document in result.documents) {
                    val myAccount: Account? = document.toObject(Account::class.java)
                    myAccount?.let {
                        it.id = document.id
                        accountList.add(it)
                    }
                }
                accounts.value = accountList
            }
        }
    }

    fun addFirestore(account: Account) {
        firestore.collection(ACCOUNT_COLLECTION).add(account)
    }

}