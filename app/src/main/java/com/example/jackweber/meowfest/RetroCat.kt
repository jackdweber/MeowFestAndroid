package com.example.jackweber.meowfest

import com.google.gson.annotations.SerializedName

class RetroCat(title1: String, url1: String, description1: String) {

    @SerializedName("title")
    var title: String = title1
    @SerializedName("image_url")
    var url: String = url1
    @SerializedName("description")
    var description: String = description1

}