package com.magangonline.magang1.feature.detailproduk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.magangonline.magang1.R
import com.magangonline.magang1.feature.addproduk.AddProdukContract
import com.magangonline.magang1.feature.addproduk.AddProdukPresenter
import com.magangonline.magang1.model.Produk
import kotlinx.android.synthetic.main.activity_detail_produk.*

class DetailProdukActivity : AppCompatActivity(), DetailProdukContract.View {

    override lateinit var presenter: DetailProdukContract.Presenter
    private var loading: AlertDialog? = null

    init {
        DetailProdukPresenter(this)
    }

    private var produk : Produk? = null
    private var kode : String? = null
    private var idProduk : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)
        loading = showDialogLoading()
//        produk = intent.getParcelableExtra("data")
        kode = intent.getStringExtra("kode")

//        et_nama.setText(produk?.nama_produk)
//        et_kode.setText(produk?.kode_produk)
//        et_harga.setText(produk?.harga_produk)
//        et_jumlah.setText(produk?.qty_produk)

        presenter.getProduk(kode)

    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onGetData(produk: Produk, id: String?) {
        idProduk = id
        et_nama.setText(produk.nama_produk)
        et_kode.setText(produk.kode_produk)
        et_harga.setText(produk.harga_produk)
        et_jumlah.setText(produk.qty_produk)
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onProcess(boolean: Boolean) {
        if (boolean) {
            loading?.show()
        } else {
            loading?.dismiss()
        }
    }

    private fun showDialogLoading(): AlertDialog {
        val view = LayoutInflater.from(this).inflate(R.layout.item_layout, null, false)
        return AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()
    }

}