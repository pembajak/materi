package belajar.android.forumkita.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import belajar.android.forumkita.R
import belajar.android.forumkita.model.Post
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_artikel.view.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    var artikel  : Post? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        artikel = intent.getParcelableExtra("artikel")

        artikel?.let {
            tvCategory.text = it.kategori
            tvJudul.text = it.judul
            tvBerita.text = it.berita
            tvTanggal.text = dateFormatter(Date(it.tanggal),"dd")
            tvBulan.text = dateFormatter(Date(it.tanggal),"MMM")
            tvPenulis.text = it.penulis.nama

            Glide.with(imagePenulis)
                .load(it.penulis.pic)
                .into(imagePenulis)

            Glide.with(image)
                .load(it.img)
                .into(image)

        }



    }

    fun dateFormatter(date: Date, formatType: String): String {
        val format = SimpleDateFormat(formatType, Locale.getDefault())
        return format.format(date)
    }
}
