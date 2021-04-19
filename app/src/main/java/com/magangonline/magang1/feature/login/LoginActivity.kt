package com.magangonline.magang1.feature.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.magangonline.magang1.R
import com.magangonline.magang1.feature.main.MainActivity
import com.magangonline.magang1.feature.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_email
import kotlinx.android.synthetic.main.activity_login.et_password
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    override lateinit var presenter: LoginContract.Presenter
    private var loading: ProgressDialog? = null

    init {
        LoginPresenter(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loading = ProgressDialog(this)
        tv_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        btn_login.setOnClickListener {
            if (handleInput()) {
                presenter.login(et_email.text.toString(), et_password.text.toString())
            }
        }

    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        loading?.dismiss()
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        loading?.dismiss()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onProcess(boolean: Boolean) {
        loading?.setMessage("Loading")
        loading?.setCancelable(false)
        loading?.show()
    }

    private fun handleInput() : Boolean {
        if (et_email.text.isEmpty()) {
            et_email.error = getString(R.string.fill_data)
            et_email.requestFocus()
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

}