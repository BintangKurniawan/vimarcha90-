package com.example.vimarcha.modeldata

import com.google.gson.annotations.SerializedName

class MovieDetailData (
    val year: String,
    val title: String,
    val type: String,
    val plot: String,
    val directors: String,
    val genres: String,
    val contentRating: String,
    val imDbRating: String,
    @SerializedName("image") val gambar: String,

    )