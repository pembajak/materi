package belajar.android.forumkita

import android.util.Log
import belajar.android.forumkita.api.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter : MainContract.Presenter {

    var view : MainContract.View? = null
    var apiService : ApiService

    val compositeDisposable = CompositeDisposable()


    init {
        apiService = ApiService()
    }

    fun attach(view : MainContract.View){
        this.view = view
    }

    override fun getListArtikel() {

       Log.d("forum","getListArtikel()")
       var sub =  apiService.apiRepository.getArtikel()
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(
               {
                   artikelRespon ->
                   Log.d("forum","getListArtikel()")
                   view?.let {
                       it.onSuccess(artikelRespon)
                   }
               },
               {
                   Log.d("forum",it.message)
                   view?.let {
                       it.onError(it.toString())
                   }
               }
           )

        compositeDisposable.add(sub)
    }
}