package com.magangonline.magang1.feature.home

import android.content.Context
import com.google.firebase.database.*
import com.magangonline.magang1.model.Produk

class HomePresenter(val context: Context, var view : HomeContract.View?) : HomeContract.Presenter {

    init {
        view?.presenter = this
    }

    override fun start() {

    }

    override fun destroy() {
        view = null
    }

    override fun getProduk() {
        view?.onProcess(true)
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Produk")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allProduk : ArrayList<Produk> = ArrayList()
                for (produkSnap in snapshot.children){
                    val produk = produkSnap.getValue(Produk::class.java)
                    if (produk != null) {
                        allProduk.add(produk)
                    }
                }
                view?.onSuccess(allProduk)
                view?.onProcess(false)
            }

            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
                view?.onProcess(false)
            }

        })
    }

}