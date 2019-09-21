package belajar.android.forumkita.api

import belajar.android.forumkita.model.ArtikelRespon
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiRepository {
    @GET("/pembajak/materi/master/db.json")
    fun getArtikel() : Observable<ArtikelRespon>
}