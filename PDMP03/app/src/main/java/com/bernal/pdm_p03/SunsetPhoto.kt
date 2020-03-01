package com.bernal.pdm_p03

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//nos permite evitar sobreescribir algunos metodos y simplificar el mantenimiento (AndroidExtensions, experimental = true)
//para transporte de alto rendimiento de datos de un componente a otro en Android.
@Parcelize
data class SunsetPhoto(val url: String) : Parcelable { //este sera nuestro modelo de datos, que solo tiene un atributo url

    companion object { //metodo estatico para devolver un array de imagenes
        fun getSunsetPhotos(): Array<SunsetPhoto> {
            return arrayOf(SunsetPhoto("https://goo.gl/32YN2B"),
                    SunsetPhoto("https://goo.gl/Wqz4Ev"),
                    SunsetPhoto("https://goo.gl/U7XXdF"),
                    SunsetPhoto("https://goo.gl/ghVPFq"),
                    SunsetPhoto("https://goo.gl/qEaCWe"),
                    SunsetPhoto("https://goo.gl/vutGmM"))
        }
    }
}
