package belajar.android.forumkita

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_artikel.view.*
import java.text.SimpleDateFormat
import java.util.*

class MainAdapter(private val list: List<Post>) :  RecyclerView.Adapter<MainAdapter.ViewHolder>()  {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent!!.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder?, position: Int) {
        val item : Post = list[position]
        holder!!.bind(item)
    }


    inner class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_artikel, parent, false)) {


        fun bind(item: Post) {

            // masukan data
            itemView.tvCategory.text = item.kategori
            itemView.tvJudul.text = item.judul
            itemView.tvDate.text = dateFormatter(Date(item.tanggal),"dd MMM yyyy")

            // Load Image
            Glide.with(itemView.image)
                .load(item.img)
                .into(itemView.image)



        }

        fun dateFormatter(date: Date, formatType: String): String {
            val format = SimpleDateFormat(formatType, Locale.getDefault())
            return format.format(date)
        }

    }
}
