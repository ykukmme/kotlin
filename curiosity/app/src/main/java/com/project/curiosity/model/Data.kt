package com.project.curiosity.model

import com.google.gson.annotations.SerializedName

data class Data (
    @SerializedName("statusCode") val statusCode : Int,
    @SerializedName("length") val length : Int,
    @SerializedName("body") val body : ArrayList<Body>
)