package com.fabiansuarez.micartera.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.fabiansuarez.micartera.R
import com.fabiansuarez.micartera.databinding.ActivityRegisterBinding
import com.fabiansuarez.micartera.util.OnOperationCallback
import com.fabiansuarez.micartera.viewmodel.RegisterActivityViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        viewModel.nameError.value = "estas aqui"

        viewModel.nameError.observe(this) { it?.let { binding.etNameRegister.error = it } }
        viewModel.emailError.observe(this) { it?.let { binding.etEmail.error = it } }
        viewModel.passwordError.observe(this) { it?.let { binding.etPass.error = it } }
        viewModel.passwordConfirmationError.observe(this
        ) { it?.let { binding.etPassConfirmation.error = it } }


        binding.btnRegister.setOnClickListener {
            if (viewModel.isFormValid()) {
                viewModel.register(object : OnOperationCallback {
                    override fun onAccountAdded() {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.user_account_was_created_succeful),
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }

                    override fun onAccountAddError(error: String) {
                        Toast.makeText(
                            applicationContext,
                            applicationContext.getString(R.string.error), Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            } else {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.please_fill_out_all_fields_correctly),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        binding.viewModel = viewModel


    }

    fun finish(view: View) {
        finish()
    }
}