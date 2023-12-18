package com.fabiansuarez.micartera.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabiansuarez.micartera.model.entity.User
import com.fabiansuarez.micartera.model.repository.UserRepository
import com.fabiansuarez.micartera.util.OnOperationCallback

class RegisterActivityViewModel(application: Application) : AndroidViewModel(application) {

    var user: User = User()
    var password: String = ""
    var passwordConfirmation: String = ""


    private val userRepository = UserRepository()

    fun register(callback: OnOperationCallback? = null) {
        userRepository.createUser(user, password, object : OnOperationCallback {
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


    // Nuevas variables para mensajes de error
    var nameError = MutableLiveData<String?>()
    var emailError = MutableLiveData<String?>()
    var passwordError = MutableLiveData<String?>()
    var passwordConfirmationError = MutableLiveData<String?>()
    fun isFormValid(): Boolean {
        // Restablece los mensajes de error
        nameError.value = null
        emailError.value = null
        passwordError.value = null
        passwordConfirmationError.value = null

        return when {
            user.name.isEmpty() || user.name == "" -> {
                nameError.value = "Please enter your name"
                false
            }

            user.email.isEmpty() || user.email == "" -> {
                emailError.value = "Please enter your email"
                false
            }

            password.isEmpty() || password == "" -> {
                passwordError.value = "Please enter your password"
                false
            }

            passwordConfirmation.isEmpty() || passwordConfirmation == "" -> {
                passwordConfirmationError.value = "Please confirm your password"
                false
            }

            password != passwordConfirmation -> {
                passwordConfirmationError.value = "Passwords do not match"
                false
            }

            else -> {
                true
            }
        }
    }

}