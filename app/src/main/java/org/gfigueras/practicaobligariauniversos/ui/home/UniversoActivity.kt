package org.gfigueras.practicaobligariauniversos.ui.home

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController

class UniversoActivity: AppCompatActivity() {
    private var controlador: IController? =null
    private var imagen:ImageView? = null
    private var titulo:TextView? = null
    private var descripcion:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.universe_page)
        controlador = Controller(this)
        imagen = findViewById(R.id.imagen_universo)
        titulo = findViewById(R.id.titulo_universo)
        descripcion = findViewById(R.id.descripcion_universo)

        Glide.with(this).load(Controller.universoSaved!!.getImagen()).into(imagen!!)
        titulo!!.text = Controller.universoSaved!!.getNombre()
        descripcion!!.text = Controller.universoSaved!!.getDescripcion()


    }

    override fun onBackPressed() {
        super.onBackPressed()
        Controller.universoSaved = null
        overridePendingTransition(R.anim.scale_up, R.anim.scale_down)
    }
}