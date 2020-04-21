package com.dary.spotyapp

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dary.spotyapp.listener.ListenerSong
import com.dary.spotyapp.models.SongModel
import com.dary.spotyapp.utils.ITEM_ALBUM
import kotlinx.android.synthetic.main.item_song.view.*

class SongHolder(val view: View,val listener: ListenerSong):RecyclerView.ViewHolder(view){
//ASI ESTABA: class SongHolder(val view: View):RecyclerView.ViewHolder(view){
    fun bingSong(songModel: SongModel)
    {
        view.txtTitleSong.text=songModel.title
        view.txtDurationSong.text=calculateTime(songModel.time)
        //songModel.time
        view.setOnClickListener{
           /* val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(songModel.url))
            if(intent.resolveActivity(view.context.packageManager)!=null){
                view.context.startActivity(intent)
            }*/

            //SE adiciono la nueva parte del lister
            listener.onClickedSong(songModel)

        }

    }



    fun calculateTime(duration:String):String{
        val minutes= duration.toInt()/1000/60
        val seconds=duration.toInt()/1000 % 60
        return "$minutes:${if (seconds<10) "0$seconds" else seconds}"
    }



}