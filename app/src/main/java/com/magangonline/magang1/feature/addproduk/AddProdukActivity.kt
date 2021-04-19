package com.magangonline.magang1.feature.addproduk

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.magangonline.magang1.R
import com.magangonline.magang1.feature.register.RegisterPresenter
import com.magangonline.magang1.model.Produk
import kotlinx.android.synthetic.main.activity_add_produk.*
import kotlinx.android.synthetic.main.activity_register.*

class AddProdukActivity : AppCompatActivity(), AddProdukContract.View {

    override lateinit var presenter: AddProdukContract.Presenter
    private var loading: AlertDialog? = null

    init {
        AddProdukPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_produk)
        loading = showDialogLoading()
        btn_tambah.setOnClickListener {
            if (handleInput()){
                val produk = Produk(
                    et_nama_produk.text.toString(),
                    et_harga_produk.text.toString(),
                    et_jumlah_produk.text.toString(),
                    et_kode_produk.text.toString()
                )
                presenter.addProduk(produk)
            }
        }
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        et_kode_produk.setText("")
        et_nama_produk.setText("")
        et_jumlah_produk.setText("")
        et_harga_produk.setText("")
    }

    override fun onProcess(boolean: Boolean) {
        if (boolean) {
            loading?.show()
        } else {
            loading?.dismiss()
        }
    }

    private fun handleInput() : Boolean {
        if (et_kode_produk.text.isEmpty()) {
            et_kode_produk.error = getString(R.string.fill_data)
            et_kode_produk.requestFocus()
            return false
        }
        if (et_nama_produk.text.isEmpty()) {
            et_nama_produk.error = getString(R.string.fill_data)
            et_nama_produk.requestFocus()
            return false
        }
        if (et_harga_produk.text.isEmpty()) {
            et_harga_produk.error = getString(R.string.fill_data)
            et_harga_produk.requestFocus()
            return false
        }
        if (et_jumlah_produk.text.isEmpty()) {
            et_jumlah_produk.error = getString(R.string.fill_data)
            et_jumlah_produk.requestFocus()
            return false
        }
        return true
    }

    private fun showDialogLoading(): AlertDialog {
        val view = LayoutInflater.from(this).inflate(R.layout.item_layout, null, false)
        return AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()
    }

}