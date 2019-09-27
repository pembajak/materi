package belajar.android.forumkita

interface MainContract {

    interface Presenter {
        fun getListArtikel()
    }

    interface View {
        fun onError(error : String)
        fun onSuccess(artikelRespon: ArtikelRespon)
    }
}