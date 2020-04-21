package com.dary.spotyapp.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


//mapear la estructuraa
@Parcelize
data class AlbumModel(
    val id: Int,
    val name: String,
    val image: String

):Parcelable

/*{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlbumModel> {
        override fun createFromParcel(parcel: Parcel): AlbumModel {
            return AlbumModel(parcel)
        }

        override fun newArray(size: Int): Array<AlbumModel?> {
            return arrayOfNulls(size)
        }
    }
}*/
