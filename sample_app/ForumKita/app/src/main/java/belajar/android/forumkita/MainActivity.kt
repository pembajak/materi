package belajar.android.forumkita

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import belajar.android.forumkita.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , MainContract.View{

    var presenter = MainPresenter()

    override fun onError(error: String) {
        Toast.makeText(this,error,Toast.LENGTH_LONG).show()
    }

    override fun onSuccess(artikelRespon: ArtikelRespon) {

        var onItemClick : RVListener<Post> = object: RVListener<Post> {
            override fun onClick(item: Post) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("artikel",item)
                startActivity(intent)
            }
        }


        var adapter = MainAdapter(artikelRespon.posts)
        adapter.listener =  onItemClick


        recycleView.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Animasi collapsing
        tvTitle.visibility = View.GONE
        tvTitleHeader.visibility = View.VISIBLE

        appBar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    tvTitle.visibility = View.VISIBLE
                    tvTitleHeader.visibility = View.GONE
                } else{
                    tvTitle.visibility = View.GONE
                    tvTitleHeader.visibility = View.VISIBLE
                }
            }
        })


        var mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL

        recycleView.layoutManager = mLayoutManager


        presenter.attach(this)
        presenter.getListArtikel()




    }



}
