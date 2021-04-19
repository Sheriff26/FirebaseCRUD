package com.magangonline.magang1.feature.profile

import com.magangonline.magang1.base.BasePresenter
import com.magangonline.magang1.base.BaseView
import com.magangonline.magang1.model.User

interface ProfileContract {

    interface View : BaseView<Presenter> {
        fun onError(message: String)
        fun onSuccess(user: User)
        fun onProcess(boolean: Boolean)
    }

    interface Presenter : BasePresenter {
        fun getUser()
    }

}