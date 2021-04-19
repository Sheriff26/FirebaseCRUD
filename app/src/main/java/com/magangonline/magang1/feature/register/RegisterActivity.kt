package com.magangonline.magang1.feature.register

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.magangonline.magang1.R
import com.magangonline.magang1.model.User
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    override lateinit var presenter: RegisterContract.Presenter
    private var loading: AlertDialog? = null

    init {
        RegisterPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        loading = showDialogLoading()
        btn_register.setOnClickListener {
            if (handleInput()) {
                val user = User(
                    et_username.text.toString(),
                    et_email.text.toString(),
                    et_date.text.toString(),
                    et_password.text.toString()
                )
                presenter.register(user)
            }
        }
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        loading?.dismiss()
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        loading?.dismiss()
    }

    override fun onProcess(boolean: Boolean) {
        loading?.show()
    }

    private fun handleInput() : Boolean {
        if (et_username.text.isEmpty()) {
            et_username.error = getString(R.string.fill_data)
            et_username.requestFocus()
            return false
        }
        if (et_email.text.isEmpty()) {
            et_email.error = getString(R.string.fill_data)
            et_email.requestFocus()
            return false
        }
        if (et_date.text.isEmpty()) {
            et_date.error = getString(R.string.fill_data)
            et_date.requestFocus()
            return false
        }
        if (et_password.text.isEmpty()) {
            et_password.error = getString(R.string.fill_data)
            et_password.requestFocus()
            return false
        }
        if (et_password.text.length < 6) {
            et_password.error = getString(R.string.six_char)
            et_password.requestFocus()
            return false
        }
        return true
    }

    private fun showDialogLoading() : AlertDialog {
        val view = LayoutInflater.from(this).inflate(R.layout.item_layout, null, false)
        return AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()
    }


}