package com.magangonline.magang1.feature.detailproduk

import com.google.firebase.database.*
import com.magangonline.magang1.feature.addproduk.AddProdukContract
import com.magangonline.magang1.model.Produk

class DetailProdukPresenter(var view: DetailProdukContract.View?) : DetailProdukContract.Presenter {

    init {
        view?.presenter = this
    }

    override fun start() {

    }

    override fun destroy() {
        view = null
    }

    override fun getProduk(kodeProduk: String?) {
        view?.onProcess(true)
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("Produk")
        ref.orderByChild("kode_produk").equalTo(kodeProduk)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        for (data in snapshot.children) {
                            val id = data.key
                            val produk = data.getValue(Produk::class.java)
                            if (produk != null) {
                                view?.onGetData(produk, id)
                                view?.onProcess(false)
                            } else {
                                view?.onError("Data tidak ditemukan")
                                view?.onProcess(false)
                            }
                        }
                    } else {
                        view?.onError("Data tidak ditemukan")
                        view?.onProcess(false)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    view?.onError(error.message)
                    view?.onProcess(false)
                }

            })

//        ref.orderByChild("kode_produk").equalTo(kodeProduk)
//            .addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    if (snapshot.exists()){
//                        for (data in snapshot.children){
//                            val id = data.key
//                            val produk = data.getValue(Produk::class.java)
//                            if (produk != null){
//                                view?.onGetData(produk, id)
//                                view?.onProcess(false)
//                            } else {
//                                view?.onError("Data tidak ditemukan")
//                                view?.onProcess(false)
//                            }
//                        }
//                    } else {
//                        view?.onError("Data tidak ditemukan")
//                        view?.onProcess(false)
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    view?.onError(error.message)
//                    view?.onProcess(false)
//                }
//
//            })

    }

    override fun updateProduk(idProduk: String, produk: Produk) {
        view?.onProcess(true)
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Produk")
        ref.child(idProduk).setValue(produk).addOnSuccessListener {
            view?.onSuccess("Success")
            view?.onProcess(false)
        }.addOnFailureListener {
            view?.onError(it.message!!)
            view?.onProcess(false)
        }

    }

    override fun deleteProduk(idProduk: String) {
        view?.onProcess(true)
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Produk")
        ref.child(idProduk).removeValue().addOnSuccessListener {
            view?.onSuccessDelete("Success")
            view?.onProcess(false)
        }.addOnFailureListener {
            view?.onError(it.message!!)
            view?.onProcess(false)
        }
    }

}