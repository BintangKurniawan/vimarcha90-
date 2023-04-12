package com.example.vimarcha.modeldata

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieData(
    val title: String?,
    val year: String?,
    val genres: String?,
    val description: String?,
    @SerializedName("image") val gambar: String?,
    @SerializedName("id") val idMovie: String?,
)
