package belajar.android.forumkita
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class ArtikelRespon(
    @SerializedName("posts")
    val posts: List<Post>
)

data class Post(
    @SerializedName("berita")
    val berita: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("judul")
    val judul: String,
    @SerializedName("kategori")
    val kategori: String,
    @SerializedName("penulis")
    val penulis: Penulis,
    @SerializedName("tanggal")
    val tanggal: Long
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readParcelable<Penulis>(Penulis::class.java.classLoader),
        source.readLong()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(berita)
        writeInt(id)
        writeString(img)
        writeString(judul)
        writeString(kategori)
        writeParcelable(penulis, 0)
        writeLong(tanggal)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Post> = object : Parcelable.Creator<Post> {
            override fun createFromParcel(source: Parcel): Post = Post(source)
            override fun newArray(size: Int): Array<Post?> = arrayOfNulls(size)
        }
    }
}

data class Penulis(
    @SerializedName("nama")
    val nama: String,
    @SerializedName("pic")
    val pic: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(nama)
        writeString(pic)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Penulis> = object : Parcelable.Creator<Penulis> {
            override fun createFromParcel(source: Parcel): Penulis = Penulis(source)
            override fun newArray(size: Int): Array<Penulis?> = arrayOfNulls(size)
        }
    }
}