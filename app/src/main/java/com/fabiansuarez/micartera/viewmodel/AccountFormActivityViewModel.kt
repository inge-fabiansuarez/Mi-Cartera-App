package com.fabiansuarez.micartera.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.fabiansuarez.micartera.model.enums.TypeAccount
import com.fabiansuarez.micartera.model.entity.Account
import com.fabiansuarez.micartera.model.repository.AccountRepository
import com.fabiansuarez.micartera.util.OnOperationCallback
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class AccountFormActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val accountRepository = AccountRepository()
    var account: Account = Account()

    private val auth = Firebase.auth
    private val currentUser = auth.currentUser


    init {
        account.userId = currentUser?.uid ?: "Anonimo"
    }

    // Lista de opciones del enumerado
    var accountTypes: List<TypeAccount> = TypeAccount.values().toList()
    var callback: OnOperationCallback? = null

    fun onSaveButtonClick() {
        accountRepository.add(account, object : OnOperationCallback {
            override fun onAccountAdded() {
                callback?.let {
                    it.onAccountAdded()
                }
            }

            override fun onAccountAddError(error: String) {
                callback?.let {
                    it.onAccountAdded()
                }
            }

        })
    }

    fun onTypeAccountSelected(selectedType: TypeAccount) {
        account?.let {
            it.accountsTypeId = selectedType
        }
    }

}