package org.gfigueras.practicaobligariauniversos.ui.universos

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController

/**
 * Esta clase funciona cuando haces click en un universo en la pagina de universos, es llamada y automodifica las imagenes y el universo guardado que es al que hemos hecho click, para settear en la vista todos los textos e imagenes correspondientes con dicho universo
 * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
 */
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

    /**
     * Overridea el metodo de volver, para que el universoSaved del Controller(Estatico) se sette a null, y añade animacion
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    override fun onBackPressed() {
        super.onBackPressed()
        Controller.universoSaved = null
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}