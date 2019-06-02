package com.example.iteventscftcheck_in.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Date {

    @SerializedName("start")
    @Expose
    var start: String? = null
}