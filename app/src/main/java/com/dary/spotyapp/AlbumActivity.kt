package com.dary.spotyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.dary.spotyapp.listener.ListenerAlbum
import com.dary.spotyapp.models.AlbumModel
import com.dary.spotyapp.repository.SpotyRespository
import com.dary.spotyapp.utils.ITEM_ALBUM
import com.dary.spotyapp.utils.ValidateInternet
import kotlinx.android.synthetic.main.activity_album.*

class AlbumActivity : AppCompatActivity(), ListenerAlbum {

    val validateInternet=ValidateInternet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        validateInternetToGetAlbums()

    }

        //createThreadToGetAlbums()

        private fun validateInternetToGetAlbums(){
            if(validateInternet.isInternetAvailable(this))
            {
                createThreadToGetAlbums()
            }else{
                //Hacer una alerta sino  tiene internet
                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage(R.string.error_internet)
                    .setPositiveButton("Reintentar"){_, _ ->
                        validateInternetToGetAlbums()
                    }
                    .setNegativeButton("Salir"){listener, _ ->
                        //listener.dismiss()
                        finish()
                    }
                    .create()
                    .show()
            }
        }

        //como se debe pintar
  //      recycleViewAlbums.layoutManager=GridLayoutManager(this, 3)

        // De esta forma se crea un for
  /*      val itemsAlbum : List<AlbumModel> =(0..20).map{ item->
            AlbumModel(item,"Titulo $item","")
        }*/

        //recycleViewAlbums.adapter=AlbumAdapter(itemsAlbum)


    override fun onClickedAlbum(bundle: Bundle?, album: AlbumModel) {
        val intent= Intent(this,SongActivity::class.java)
        intent.putExtra(ITEM_ALBUM,album)
        startActivity(intent,bundle)
    }

    private fun createThreadToGetAlbums() {
       val thread=Thread(Runnable{
           getAlbumsFromReposity()
       })
        thread.start()
    }

    private fun getAlbumsFromReposity() {
       try {
           val repository = SpotyRespository()
           val result: List<AlbumModel> = repository.getAlbums(5)
           loadAdapter(result)
       }catch (e:Exception){
           runOnUiThread {
               Toast.makeText(this, e.message ?: "Error", Toast.LENGTH_LONG).show()
           }
       }
    }

    private fun loadAdapter(result:List<AlbumModel>){
        runOnUiThread{
            recycleViewAlbums.layoutManager=GridLayoutManager(this,2)
            recycleViewAlbums.adapter=AlbumAdapter(result, this)
        }
    }


}
