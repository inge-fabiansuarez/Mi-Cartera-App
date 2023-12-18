package com.fabiansuarez.micartera.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fabiansuarez.micartera.model.entity.User
import com.fabiansuarez.micartera.model.repository.UserRepository
import com.fabiansuarez.micartera.util.OnOperationCallback

class LoginActivityViewModel(application: Application) : AndroidViewModel(application) {


    var user: User = User()
    var password: String = ""

    private val userRepository = UserRepository()

    fun login(callback: OnOperationCallback? = null) {
        userRepository.login(user, password, object : OnOperationCallback {
            override fun onAccountAdded() {
                callback?.let {
                    Log.i("quier111", user.email + password)
                    it.onAccountAdded()
                }
            }

            override fun onAccountAddError(error: String) {
                callback?.let {
                    Log.i("quier1111", user.email + password)
                    it.onAccountAddError(error)
                }
            }

        })
    }


    // Nuevas variables para mensajes de error
    var emailError = MutableLiveData<String?>()
    var passwordError = MutableLiveData<String?>()
    fun isFormValid(): Boolean {
        // Restablece los mensajes de error
        emailError.value = null
        passwordError.value = null
        return when {
            user.email.isEmpty() || user.email == "" -> {
                emailError.value = "Please enter your email"
                false
            }

            password.isEmpty() || password == "" -> {
                passwordError.value = "Please enter your password"
                false
            }

            else -> {
                true
            }
        }
    }

}