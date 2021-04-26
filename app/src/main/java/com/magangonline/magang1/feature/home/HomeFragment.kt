package com.magangonline.magang1.feature.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter
    private var loading: AlertDialog? = null
    private var produkAdapter: ProdukAdapter? = null

    private var listProduk : ArrayList<Produk>? = null
    private var searchListProduk : ArrayList<Produk>? = null

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

        search.addTextChangedListener(onSearchChange())

    }

    private fun onSearchChange() : TextWatcher{
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchListProduk = ArrayList()
                if (s?.isEmpty()!!) {
                    searchListProduk = listProduk
                } else {
                    searchListProduk = filterProduk(s.toString())
                }
                val onClickItem = object : ProdukAdapter.OnClickItem {
                    override fun onClickItem(data: Produk) {
                        startActivity(Intent(requireContext(), DetailProdukActivity::class.java)
                            .putExtra("kode", data.kode_produk)
                        )
                    }

                }
                produkAdapter = ProdukAdapter(searchListProduk, requireContext(), onClickItem)
                rv_produk.adapter = produkAdapter
                rv_produk.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
    }

    private fun filterProduk(search: String) : ArrayList<Produk> {
        val result: ArrayList<Produk> = ArrayList()
        if (listProduk?.size != null && listProduk?.size != 0){
            for (i in listProduk?.indices!!){
                if (listProduk!!.get(i).nama_produk.toLowerCase(Locale.ROOT).contains(search.toLowerCase(Locale.ROOT))){
                    result.add(listProduk!![i])
                }
            }
        }
        return result
    }

    override fun onError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onSuccess(produk: ArrayList<Produk>) {
        listProduk = produk
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