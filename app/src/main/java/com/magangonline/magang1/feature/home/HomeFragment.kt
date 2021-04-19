package com.magangonline.magang1.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.magangonline.magang1.R
import com.magangonline.magang1.adapter.ProdukAdapter
import com.magangonline.magang1.feature.addproduk.AddProdukActivity
import com.magangonline.magang1.feature.detailproduk.DetailProdukActivity
import com.magangonline.magang1.model.Produk
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter
    private var loading: AlertDialog? = null
    private var produkAdapter: ProdukAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        HomePresenter(requireContext(), this)

        loading = showDialogLoading()

        presenter.getProduk()

        fab_produk.setOnClickListener {
            startActivity(Intent(requireContext(), AddProdukActivity::class.java))
        }

    }

    override fun onError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onSuccess(produk: ArrayList<Produk>) {
        val onClickItem = object : ProdukAdapter.OnClickItem {
            override fun onClickItem(data: Produk) {
                startActivity(Intent(requireContext(), DetailProdukActivity::class.java)
                    .putExtra("kode", data.kode_produk)
                )
            }

        }
//        val onClickItem = object : ProdukAdapter.OnClickItem{
//            override fun onClickItem(data: Produk) {
//                startActivity(Intent(requireContext(), DetailProdukActivity::class.java)
//                    .putExtra("data", data)
//                    .putExtra("kode", data.kode_produk)
//                )
//            }
//
//        }
        produkAdapter = ProdukAdapter(produk, requireContext(), onClickItem)
        rv_produk.adapter = produkAdapter
        rv_produk.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onProcess(boolean: Boolean) {
        if (boolean) {
            loading?.show()
        } else {
            loading?.dismiss()
        }
    }

    private fun showDialogLoading(): AlertDialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.item_layout, null, false)
        return AlertDialog.Builder(requireContext())
            .setView(view)
            .setCancelable(false)
            .create()
    }


}