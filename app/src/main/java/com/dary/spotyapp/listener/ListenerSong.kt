package com.dary.spotyapp.listener

import com.dary.spotyapp.models.SongModel

interface ListenerSong {
    fun onClickedSong(
        song: SongModel
    )
}