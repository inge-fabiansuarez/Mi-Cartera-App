package com.fabiansuarez.micartera.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.fabiansuarez.micartera.R
import com.fabiansuarez.micartera.databinding.ActivityLoginBinding
import com.fabiansuarez.micartera.util.OnOperationCallback
import com.fabiansuarez.micartera.viewmodel.LoginActivityViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.btnRegisterLogin.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        viewModel.emailError.observe(this) { it?.let { binding.etEmail.error = it } }
        viewModel.passwordError.observe(this) { it?.let { binding.etPass.error = it } }

        binding.btnLogin.setOnClickListener {
            if (viewModel.isFormValid()) {
                viewModel.login(object : OnOperationCallback {
                    override fun onAccountAdded() {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.sign_in_succeful),
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    }

                    override fun onAccountAddError(error: String) {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.invalid_credencials_msg),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.i(getString(R.string.error_credenciales), error)
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
}