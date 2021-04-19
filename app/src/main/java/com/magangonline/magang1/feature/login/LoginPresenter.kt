package com.magangonline.magang1.feature.login

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class LoginPresenter(val context: Context, var view : LoginContract.View?) : LoginContract.Presenter {

    init {
        view?.presenter = this
    }

    override fun start() {

    }

    override fun destroy() {
        view = null
    }

    override fun login(email: String, pass: String) {
        view?.onProcess(true)
        val firebaseAuth : FirebaseAuth = Firebase.auth
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                view?.onSuccess("Success")
            } else {
                view?.onError(it.exception?.message!!)
            }
        }
    }


}