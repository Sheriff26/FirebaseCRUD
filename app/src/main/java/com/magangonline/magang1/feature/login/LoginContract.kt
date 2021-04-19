package com.magangonline.magang1.feature.login

import com.magangonline.magang1.base.BasePresenter
import com.magangonline.magang1.base.BaseView

interface LoginContract {

    interface View : BaseView<Presenter> {
        fun onError(message: String)
        fun onSuccess(message: String)
        fun onProcess(boolean: Boolean)
    }

    interface Presenter : BasePresenter {
        fun login(email: String, pass: String)
    }

}