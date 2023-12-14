package com.fabiansuarez.micartera.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.fabiansuarez.micartera.view.adapter.AccountListAdapter
import com.fabiansuarez.micartera.R
import com.fabiansuarez.micartera.databinding.ActivityAccountListBinding
import com.fabiansuarez.micartera.model.entity.Account
import com.fabiansuarez.micartera.viewmodel.AccountListActivityViewModel

class AccountListActivity : AppCompatActivity() {

    private val viewModel: AccountListActivityViewModel by viewModels()
    private lateinit var binding: ActivityAccountListBinding

    private lateinit var accountListAdapter: AccountListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_account_list)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_list)
        binding.fabAddListAccount.setOnClickListener {
            startActivity(Intent(applicationContext, AccountFormActivity::class.java))
        }

        accountListAdapter = AccountListAdapter()

        binding.rvListAccountActivity.apply {
            layoutManager = LinearLayoutManager(this@AccountListActivity)
            adapter = accountListAdapter
        }
        viewModel.accountList.observe(this, {
            accountListAdapter.refresh(it as ArrayList<Account>)
        })
    }
}