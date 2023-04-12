package com.example.vimarcha.modeldata

import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("searchType") val type:String,
    @SerializedName("expression") val express:String,
    val results:List<MovieData>
)
