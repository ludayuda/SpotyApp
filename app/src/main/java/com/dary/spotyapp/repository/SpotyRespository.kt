package com.dary.spotyapp.repository

import com.dary.spotyapp.Service.ServiceFactory
import com.dary.spotyapp.Service.SpotyServices
import com.dary.spotyapp.models.AlbumModel
import com.dary.spotyapp.models.AlbumObjectModel
import com.dary.spotyapp.models.SongModel
import com.dary.spotyapp.models.SongObjectModel

class SpotyRespository {

    private var spotyServices: SpotyServices
    init{
        val servicesFactory= ServiceFactory()
        spotyServices=servicesFactory.getInstanceSpotyService()
    }

    fun getAlbums(artist: Int) : List<AlbumModel>{
        try{
            val call: retrofit2.Call<List<AlbumObjectModel>> = spotyServices.getAlbums(artist)
            val response=call.execute()

            //si la variable es exitosa se valida
            if(response.isSuccessful){
                return response.body()!![0].albums
            }else{
                throw Exception(response.errorBody().toString())
            }

        }catch (exception: Exception){
            throw exception
        }

    }


    fun getSongsByAlbum(albumId:Int):List<SongModel>{
        try{
            val call: retrofit2.Call<List<SongObjectModel>> = spotyServices.getSongsByAlbum(albumId)
            val response=call.execute()

            //si la variable es exitosa se valida
            if(response.isSuccessful){
                return response.body()!![0].songs
            }else{
                throw Exception(response.errorBody().toString())
            }

        }catch (exception: Exception){
            throw exception
        }



    }


}