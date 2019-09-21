package belajar.android.forumkita.api

import belajar.android.forumkita.model.ArtikelRespon
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiRepository {
    @GET("pembajak/materi/db")
    fun getArtikel() : Observable<ArtikelRespon>
}