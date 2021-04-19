package com.magangonline.magang1.feature.detailproduk

import com.magangonline.magang1.base.BasePresenter
import com.magangonline.magang1.base.BaseView
import com.magangonline.magang1.model.Produk

interface DetailProdukContract {

    interface View : BaseView<Presenter> {
        fun onError(message: String)
        fun onGetData(produk: Produk, id: String?)
        fun onSuccess(message: String)
        fun onProcess(boolean: Boolean)
    }

    interface Presenter : BasePresenter {
        fun getProduk(kodeProduk: String?)
    }

}