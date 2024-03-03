package myRecycler

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

data class Foods( val name: String, val uri: Uri) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readParcelable(Uri::class.java.classLoader)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeParcelable(uri, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Foods> {
        override fun createFromParcel(parcel: Parcel): Foods {
            return Foods(parcel)
        }

        override fun newArray(size: Int): Array<Foods?> {
            return arrayOfNulls(size)
        }
    }
}
