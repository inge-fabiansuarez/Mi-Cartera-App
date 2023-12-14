package com.fabiansuarez.micartera.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fabiansuarez.micartera.model.entity.Account
import com.fabiansuarez.micartera.model.repository.AccountRepository

class AccountListActivityViewModel(application: Application) : AndroidViewModel(application){
    private val accountRepository = AccountRepository()

    // LiveData para la lista de cuentas
    val accountList: LiveData<List<Account>> = accountRepository.getAllAccounts()
}