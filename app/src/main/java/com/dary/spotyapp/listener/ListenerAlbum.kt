package com.dary.spotyapp.listener

import android.os.Bundle
import com.dary.spotyapp.models.AlbumModel

interface ListenerAlbum {
    fun onClickedAlbum(
        bundle: Bundle?,
        album: AlbumModel
    )
}