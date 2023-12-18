package com.fabiansuarez.micartera.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.fabiansuarez.micartera.R
import com.fabiansuarez.micartera.databinding.ActivityAccountFormBinding
import com.fabiansuarez.micartera.util.OnOperationCallback
import com.fabiansuarez.micartera.viewmodel.AccountFormActivityViewModel

class AccountFormActivity : AppCompatActivity() {

    private val viewModel: AccountFormActivityViewModel by viewModels()
    private lateinit var binding: ActivityAccountFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_account_form)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_form)
        viewModel.callback = object : OnOperationCallback {
            override fun onAccountAdded() {
                Toast.makeText(
                    applicationContext,
                    applicationContext.getString(R.string.saved), Toast.LENGTH_SHORT
                ).show()
                finish()
            }

            override fun onAccountAddError(error: String) {
                Toast.makeText(
                    applicationContext,
                    applicationContext.getString(R.string.error), Toast.LENGTH_SHORT
                ).show()
            }

        }

        binding.viewModel = viewModel

    }
}