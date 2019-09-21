package belajar.android.forumkita

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import belajar.android.forumkita.detail.DetailActivity
import belajar.android.forumkita.model.ArtikelRespon
import belajar.android.forumkita.model.Post
import belajar.android.forumkita.utils.RVListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , MainContract.View{

    var presenter = MainPresenter()
    var adapter  = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attach(this)


        // OnClickListener
        var onItemClick : RVListener<Post> = object: RVListener<Post> {
            override fun onClick(item: Post) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("artikel",item)
                startActivity(intent)
            }
        }

        adapter.listener = onItemClick


        var mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL

        recycleView.adapter = adapter
        recycleView.isNestedScrollingEnabled = true
        recycleView.layoutManager = mLayoutManager

        presenter.getListArtikel()


    }


    override fun onError(error: String) {

    }

    override fun onSuccess(artikelRespon: ArtikelRespon) {


        Log.d("forum",">>>> onSuccess")

       adapter.submitList(artikelRespon.posts)
    }
}
