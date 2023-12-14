package com.fabiansuarez.micartera.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fabiansuarez.micartera.model.entity.Account
import com.fabiansuarez.micartera.util.OnAccountOperationCallback
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AccountRepository {
    private val ACCOUNT_COLLECTION: String = "accounts"
    var account: MutableLiveData<Account> = MutableLiveData()
    var accounts: MutableLiveData<List<Account>> = MutableLiveData()

    private val firestore: FirebaseFirestore = Firebase.firestore
    init {
        loadAllAccounts()
    }

    fun add(account: Account, callback: OnAccountOperationCallback? = null) {
        firestore.collection(ACCOUNT_COLLECTION).add(account).addOnSuccessListener {
            callback?.let {
                it.onAccountAdded()
            }
        }.addOnFailureListener { exception ->
            callback?.let {
                it.onAccountAddError(exception)
            }
        }
    }
    fun getAllAccounts(): LiveData<List<Account>> {
        return accounts;
    }

    fun loadAllAccounts() {
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


}