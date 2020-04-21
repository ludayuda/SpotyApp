package com.dary.spotyapp.models

import com.google.gson.annotations.SerializedName

data class AlbumObjectModel (
    //
    //Para cuando no queremos llamar la variable igual a la que tiene el web services @SerializedName("artist")
    @SerializedName("artist")
    val artista: Int,
    val albums: List<AlbumModel>
)