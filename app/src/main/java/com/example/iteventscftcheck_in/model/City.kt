package com.example.iteventscftcheck_in.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class City {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("nameRus")
    @Expose
    var nameRus: String? = null
}