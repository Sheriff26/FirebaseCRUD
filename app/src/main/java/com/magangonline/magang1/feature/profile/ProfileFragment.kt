package com.magangonline.magang1.feature.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.magangonline.magang1.R
import com.magangonline.magang1.model.User
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), ProfileContract.View {

    override lateinit var presenter: ProfileContract.Presenter
    private var loading: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ProfilePresenter(requireContext(), this)

        loading = showDialogLoading()
        presenter.getUser()
    }

    override fun onError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        loading?.dismiss()
    }

    override fun onSuccess(user: User) {
        tv_nama_user.text = user.nama
        tv_nama.text = user.nama
        tv_email_user.text = user.email
        tv_tgl_lahir.text = user.lahir
        loading?.dismiss()
    }

    override fun onProcess(boolean: Boolean) {
        loading?.show()
    }

    private fun showDialogLoading(): AlertDialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.item_layout, null, false)
        return AlertDialog.Builder(requireContext())
            .setView(view)
            .setCancelable(false)
            .create()
    }

}