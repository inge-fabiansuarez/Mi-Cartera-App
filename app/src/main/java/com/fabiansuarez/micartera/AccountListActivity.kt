package com.fabiansuarez.micartera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.fabiansuarez.micartera.databinding.ActivityAccountListBinding

class AccountListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_account_list)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_list)
        binding.fabAddListAccount.setOnClickListener {
            startActivity(Intent(applicationContext, AccountFormActivity::class.java))
        }

    }
}