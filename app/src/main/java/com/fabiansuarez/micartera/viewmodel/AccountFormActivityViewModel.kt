package com.fabiansuarez.micartera.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.fabiansuarez.micartera.model.enums.TypeAccount
import com.fabiansuarez.micartera.model.entity.Account
import com.fabiansuarez.micartera.model.repository.AccountRepository
import com.fabiansuarez.micartera.util.OnAccountOperationCallback


class AccountFormActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val accountRepository = AccountRepository()
    var account: Account = Account()

    // Lista de opciones del enumerado
    var accountTypes: List<TypeAccount> = TypeAccount.values().toList()
    var callback: OnAccountOperationCallback? = null

    fun onSaveButtonClick() {
        accountRepository.add(account, object : OnAccountOperationCallback {
            override fun onAccountAdded() {
                callback?.let {
                    it.onAccountAdded()
                }
            }

            override fun onAccountAddError(error: Exception) {
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