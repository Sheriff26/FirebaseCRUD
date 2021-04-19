package com.magangonline.magang1.feature.home

import com.magangonline.magang1.base.BasePresenter
import com.magangonline.magang1.base.BaseView
import com.magangonline.magang1.model.Produk

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun onError(message: String)
        fun onSuccess(produk: ArrayList<Produk>)
        fun onProcess(boolean: Boolean)
    }

    interface Presenter : BasePresenter {
        fun getProduk()
    }

}