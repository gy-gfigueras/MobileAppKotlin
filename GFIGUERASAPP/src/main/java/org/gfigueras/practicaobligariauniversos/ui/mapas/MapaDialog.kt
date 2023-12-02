package org.gfigueras.practicaobligariauniversos.ui.mapas
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.model.entities.Mapa

/**
 * Clase que genera un dialogo personalizado, del mapa pulsado, con la foto de el mapa de fondo y la descripcion
 * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
 */
class MapaDialog(context: Context, private val mapa: Mapa) : AlertDialog(context) {

    init {
        createDialogView()
    }

    /**
     * Crea el Dialog, con la imagen y la descripcion del mapa pulsado
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    private fun createDialogView() {
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.dialog_mapa, null)

        // Configurar la imagen del mapa como fondo del diálogo
        val fondoDialogImageView: ImageView = dialogView.findViewById(R.id.fondoDialogImageView)
        Glide.with(context)
            .load(mapa.getImagen())
            .transform(RoundedCorners(40)) // Ajusta el valor a tu gusto
            .into(fondoDialogImageView)

        // Configurar la descripción del mapa en un TextView
        val descripcionTextView: TextView = dialogView.findViewById(R.id.descripcionTextView)
        descripcionTextView.text = mapa.getDescripcion()

        // Aquí puedes personalizar el diseño del diálogo según tus necesidades
        // Por ejemplo, podrías configurar otros elementos como texto, botones, etc.

        setView(dialogView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configura cualquier otra configuración del diálogo aquí si es necesario
    }
}
