package com.dary.spotyapp

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.dary.spotyapp.listener.ListenerAlbum
import com.dary.spotyapp.models.AlbumModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_album.view.*

//Se crea la variable data de tipo List y se selecciona el modelo y
//RecyclerView.Adapter<AlbumAdapter.Holder>() de que propiedad va a extender
class AlbumAdapter(val data: List<AlbumModel>, val listener: ListenerAlbum) :
    RecyclerView.Adapter<AlbumAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    //
    //   override fun getItemCount(): Int{
    //       return data.size
    //   }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindItem(data[position])

    }

    inner class Holder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(itemAlbumModel: AlbumModel) {
            Picasso.with(view.context).load(itemAlbumModel.image).into(view.imgAlbum)
            view.txtTitle.text = itemAlbumModel.name
            view.setOnClickListener {
                /*  val intent = Intent(view.context, SongActivity::class.java)
                  val param1 : Pair<View, String> = Pair(view.imgAlbum, "transitionAlbumImage")
                  val param2 : Pair<View, String> = Pair(view.txtTitle, "transitionAlbumTitle")
                 // val animation: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation( view.context as Activity,param1,     param2                    )
                  view.context.startActivity(intent)*/


                val params = ArrayList<androidx.core.util.Pair<View, String>>()
                params.add(androidx.core.util.Pair(view.imgAlbum, "transitionAlbumImage"))
                params.add(androidx.core.util.Pair(view.txtTitle, "transitionAlbumTitle"))
                //  val intent = Intent(view.context, SongActivity::class.java)
                val paramsArray: Array<androidx.core.util.Pair<View, String>> =
                    params.toTypedArray()
                val animation: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        view.context as Activity,
                        *paramsArray
                    )

                // view.context.startActivity(intent, animation.toBundle())
                listener.onClickedAlbum(animation.toBundle(), itemAlbumModel)
            }
        }

    }
}