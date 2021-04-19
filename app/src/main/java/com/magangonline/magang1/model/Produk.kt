package com.magangonline.magang1.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Produk (
    var nama_produk: String = "",
    var harga_produk: String = "",
    var qty_produk: String = "",
    var kode_produk: String = ""
) : Parcelable