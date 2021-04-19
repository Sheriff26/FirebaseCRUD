package com.magangonline.magang1.feature.profile

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.magangonline.magang1.model.User

class ProfilePresenter(val context: Context, var view : ProfileContract.View?) : ProfileContract.Presenter {

    init {
        view?.presenter = this
    }

    override fun start() {

    }

    override fun destroy() {
        view = null
    }

    override fun getUser() {
        view?.onProcess(true)
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("User")
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser
        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val users : ArrayList<User>? = null
                for (dataSnap in snapshot.children){
                    val user = dataSnap.getValue(User::class.java)
                    users?.add(user!!)
                    if (currentUser.email.equals(user?.email)){
                        view?.onSuccess(user!!)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
            }

        })
    }

}