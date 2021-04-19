package com.magangonline.magang1.feature.register

import com.magangonline.magang1.base.BasePresenter
import com.magangonline.magang1.base.BaseView
import com.magangonline.magang1.model.User

interface RegisterContract {

    interface View : BaseView<Presenter> {
        fun onError(message: String)
        fun onSuccess(message: String)
        fun onProcess(boolean: Boolean)
    }

    interface Presenter : BasePresenter {
        fun register(user: User)
    }

}