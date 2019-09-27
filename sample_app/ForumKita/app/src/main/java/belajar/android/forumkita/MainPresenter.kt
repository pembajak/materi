package belajar.android.forumkita

import android.util.Log
import belajar.android.forumkita.api.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter : MainContract.Presenter {

    private var view : MainContract.View? = null
    private var apiService : ApiService
    private  val compositeDisposable = CompositeDisposable()

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
                    error ->
                    Log.d("forum",error.message)
                    view?.let {
                        it.onError(error.message!!)
                    }
                }
            )
        compositeDisposable.add(sub)
    }
}