package myRecycler

import android.net.Uri
import android.os.Parcelable
import android.os.Parcel

data class Bills (
    var description:String, var value:Float, var date:String, var uri: Uri
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readString()!!,
        parcel.readParcelable(Uri::class.java.classLoader)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeFloat(value)
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