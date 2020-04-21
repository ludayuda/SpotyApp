package com.dary.spotyapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dary.spotyapp.listener.ListenerSong
import com.dary.spotyapp.models.AlbumModel
import com.dary.spotyapp.models.SongModel
import com.dary.spotyapp.repository.SpotyRespository
import com.dary.spotyapp.utils.ITEM_ALBUM
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album.*
import kotlinx.android.synthetic.main.activity_song.*

class SongActivity : AppCompatActivity(),ListenerSong {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        rvSongs.layoutManager=LinearLayoutManager(this)
       // val songs=(0..20).map{
       //     SongModel("Cancion $it", "1:10","")
      //  }

        //val adapterSong=SongAdapter(songs)
       // rvSongs.adapter=adapterSong

      //ESTABA AQUI
        var album:AlbumModel?=null

        intent?.let {myIntent->
            album =myIntent.getParcelableExtra<AlbumModel>(ITEM_ALBUM)
            Picasso.with(this) .load(album!!.image).into(imgHeaderDetail)
            txtTitleDetail.text=album!!.name
        }

        createThreadToGetSongs(album!!.id)
    }

    override fun onClickedSong(song: SongModel) {

        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(song.url))
        if(intent.resolveActivity(this.packageManager)!=null){
            this.startActivity(intent)
        }
    }

    private fun  createThreadToGetSongs(idAlbum:Int){
        val thread=Thread(Runnable{
            getSongsFromReposity(idAlbum)
        })
        thread.start()
    }

    private fun getSongsFromReposity(idAlbum:Int) {
        try {
            val repository = SpotyRespository()
            val result: List<SongModel> = repository.getSongsByAlbum(idAlbum)
            loadAdapter(result)
        }catch (e:Exception){
            runOnUiThread {
                Toast.makeText(this,e.message?:"Error", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun loadAdapter(result: List<SongModel>) {
        runOnUiThread{

            //ASI ESTABA:
            //val adapterSong=SongAdapter(result)
            val adapterSong=SongAdapter(result,this)
            rvSongs.adapter=adapterSong

        }
    }




}
