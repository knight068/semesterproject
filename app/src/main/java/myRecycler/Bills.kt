package myRecycler

import android.net.Uri
import android.os.Parcelable
import android.os.Parcel

data class Bills (
    val description:String,val value:Double,val date:String, val uri: Uri
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readParcelable(Uri::class.java.classLoader)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeDouble(value)
        parcel.writeString(date)
        parcel.writeParcelable(uri, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bills> {
        override fun createFromParcel(parcel: Parcel): Bills {
            return Bills(parcel)
        }

        override fun newArray(size: Int): Array<Bills?> {
            return arrayOfNulls(size)
        }
    }
}