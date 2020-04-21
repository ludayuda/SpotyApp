package com.dary.spotyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dary.spotyapp.listener.ListenerSong
import com.dary.spotyapp.models.SongModel

class SongAdapter(val listSong: List<SongModel>,val listener:ListenerSong) : RecyclerView.Adapter<SongHolder>() {
// ASI ESTABA class SongAdapter(val listSong: List<SongModel>) : RecyclerView.Adapter<SongHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        return SongHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_song,
                parent,
                false
            ), listener
        )
   // listener.onClickedSong(listSong)
    //AQUI Iria el liste

    }

    override fun getItemCount(): Int {
        return listSong.size
    }

    //llega cuando hay un solo item
    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bingSong(listSong[position])
    }
}