package com.dary.spotyapp.models

import com.google.gson.annotations.SerializedName

//se debe cambiar las llaves por parentesis
data class SongModel (
    @SerializedName("name")
    val title:String,
    @SerializedName("duration_ms")
    val time:String,
    @SerializedName("spotify_url")
    val url:String


)
