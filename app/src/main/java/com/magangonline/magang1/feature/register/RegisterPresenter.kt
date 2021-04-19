package com.magangonline.magang1.feature.register

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.magangonline.magang1.model.User

class RegisterPresenter(var view : RegisterContract.View?) : RegisterContract.Presenter {

    init {
        view?.presenter = this
    }

    override fun start() {

    }

    override fun destroy() {
        view = null
    }

    override fun register(user: User) {
        view?.onProcess(true)
        val firebaseAuth : FirebaseAuth = Firebase.auth
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("User")
        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener { it ->
            if (it.isSuccessful){
                ref.push().setValue(user).addOnCompleteListener {
                    view?.onSuccess("Success")
                }.addOnFailureListener {
                    view?.onError(it.message!!)
                }
            } else {
                view?.onError(it.exception?.message!!)
            }
        }
    }

}