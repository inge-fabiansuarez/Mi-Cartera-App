package com.fabiansuarez.micartera.model.repository

import android.util.Log
import com.fabiansuarez.micartera.model.entity.User
import com.fabiansuarez.micartera.util.OnOperationCallback
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserRepository {
    private var auth: FirebaseAuth = Firebase.auth

    fun createUser(user: User, password: String, callback: OnOperationCallback? = null) {
        auth.createUserWithEmailAndPassword(user.email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback?.let {
                    it.onAccountAdded()
                }
            } else {
                callback?.let {
                    it.onAccountAddError(task.exception.toString())
                }
            }
        }
    }

    fun login(user: User, password: String, callback: OnOperationCallback? = null) {

        auth.signInWithEmailAndPassword(user.email, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                callback?.let {
                    it.onAccountAdded()
                }
            } else {
                callback?.let {
                    it.onAccountAddError(task.exception.toString())
                }
            }
        }
    }
}