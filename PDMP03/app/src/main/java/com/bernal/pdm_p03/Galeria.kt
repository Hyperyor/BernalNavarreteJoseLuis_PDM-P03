package com.bernal.pdm_p03

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Galeria : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageGalleryAdapter: ImageGalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria)

        val layoutManager = GridLayoutManager(this, 2)
        recyclerView = findViewById(R.id.rv_images) //tomamos el recycleview
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        //creamos un adaptador con las imagenes del array estatico del modelo
        imageGalleryAdapter = ImageGalleryAdapter(this, SunsetPhoto.getSunsetPhotos())
    }

    override fun onStart() {
        super.onStart()
        recyclerView.adapter = imageGalleryAdapter //colocamos el adaptador(las imagenes)


    }

    //adapter que necesita el recycleview
    //Se encarga de tomar cada una de las imagenes y colocarlas en su posicion correspondiente,
    //ademas de cargarlas de internet y darles un onClickListener
    private inner class ImageGalleryAdapter(val context: Context, val sunsetPhotos: Array<SunsetPhoto>)
        : RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageGalleryAdapter.MyViewHolder {
            val context = parent.context
            val inflater = LayoutInflater.from(context)
            val photoView = inflater.inflate(R.layout.item_image, parent, false)
            return MyViewHolder(photoView)
        }


        override fun onBindViewHolder(holder: ImageGalleryAdapter.MyViewHolder, position: Int) {
            val sunsetPhoto = sunsetPhotos[position]
            val imageView = holder.photoImageView

            //picasso se encargara de manejar la cache y la memoria en disco por nosotros
            //de forma eficiente (se puede modificar)
            //picasso primero comprueba  la cache, si no encuentra la imagen mira en disco y si no esta
            //en disco, por ultimo, hace la peticion a internet
            Picasso.get() //llama a la instancia (singleton) de picasso
                    .load(sunsetPhoto.url) //carga la imagen
                    .placeholder(R.drawable.placeholder) //imagen por defecto mientras se carga
                    .error(R.drawable.error) //imagen de error
                    .fit() //la ajusta
                    .tag(context)
                    .into(imageView) //item_image en el que se inserta la imagen

        }

        override fun getItemCount(): Int {
            return sunsetPhotos.size
        }

        //crea un viewholder por cada elemento de la lista de imagenes
        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

            var photoImageView: ImageView = itemView.findViewById(R.id.iv_photo)

            init {
                itemView.setOnClickListener(this)
            }

            //funcion para mostrar una imagen con mas detalle al clickar en ella
            override fun onClick(view: View) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) { //comprobamos que sea una imagen valida
                    val sunsetPhoto = sunsetPhotos[position] //tomamos el elemento concreto
                    val intent = Intent(context, SunsetPhotoActivity::class.java).apply {
                        putExtra(SunsetPhotoActivity.EXTRA_SUNSET_PHOTO, sunsetPhoto) //le enviamos la imagen seleccionada al activity
                    }
                    startActivity(intent)
                }
            }
        }
    }
}
