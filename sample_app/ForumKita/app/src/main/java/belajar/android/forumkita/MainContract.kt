package belajar.android.forumkita

import belajar.android.forumkita.model.ArtikelRespon

interface MainContract {

    interface Presenter {
        fun getListArtikel()
    }

    interface View {
        fun onError(error : String)
        fun onSuccess(artikelRespon: ArtikelRespon)
    }
}