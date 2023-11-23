package org.gfigueras.practicaobligariauniversos.ui.home
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
import org.gfigueras.practicaobligariauniversos.model.entities.Universo

class UniverseDialog(context: Context, private val universo: Universo) : AlertDialog(context) {

    init {
        createDialogView()
    }

    private fun createDialogView() {
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.dialog_mapa, null)

        // Configurar la imagen del mapa como fondo del diálogo
        val fondoDialogImageView: ImageView = dialogView.findViewById(R.id.fondoDialogImageView)
        Glide.with(context)
            .load(universo.getImagen())
            .transform(RoundedCorners(40)) // Ajusta el valor a tu gusto
            .into(fondoDialogImageView)
        // Configurar la descripción del mapa en un TextView
        val descripcionTextView: TextView = dialogView.findViewById(R.id.descripcionTextView)
        descripcionTextView.text = universo.getDescripcion()


        setView(dialogView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configura cualquier otra configuración del diálogo aquí si es necesario
    }
}
