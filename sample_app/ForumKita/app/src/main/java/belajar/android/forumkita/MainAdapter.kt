package belajar.android.forumkita

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import belajar.android.forumkita.model.Post
import belajar.android.forumkita.utils.RVListener
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_artikel.view.*
import java.text.SimpleDateFormat
import java.util.*

class MainAdapter : ListAdapter<Post, MainAdapter.ViewHolder>(MainDiff())  {

    var listener: RVListener<Post>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artikel, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(item: Post) {

            itemView.tvCategory.text = item.kategori
            itemView.tvJudul.text = item.judul
            itemView.tvDate.text = dateFormatter(Date(item.tanggal),"dd MMM yyyy")

            Glide.with(itemView.image)
                 .load(item.img)
                 .into(itemView.image)

            listener?.let {
                itemOnClick ->
                itemView.setOnClickListener {
                    itemOnClick.onClick(item)
                }
            }

        }

        fun dateFormatter(date: Date, formatType: String): String {
            val format = SimpleDateFormat(formatType, Locale.getDefault())
            return format.format(date)
        }
    }


    class MainDiff : DiffUtil.ItemCallback<Post>() {

        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            var contentOld = "${oldItem.id}"
            var contentNew = "${newItem.id}"
            return contentOld.equals(contentNew)
        }
    }


}