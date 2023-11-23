package org.gfigueras.practicaobligariauniversos.ui.gallery
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.model.entities.Mapa

class MapaAdapter(private val context: Context, private val mapas: List<Mapa>) :
    RecyclerView.Adapter<MapaAdapter.MapaViewHolder>() {

    class MapaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val descripcionTextView: TextView = itemView.findViewById(R.id.descripcionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_mapa, parent, false)
        return MapaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MapaViewHolder, position: Int) {
        val mapa = mapas[position]

        // Carga la imagen con Glide según la URL almacenada en la clase Mapa
        Glide.with(context).load(mapa.getImagen()).into(holder.imageView)

        holder.nombreTextView.text = mapa.getNombre()
        holder.descripcionTextView.text = mapa.getUniverso().getNombre()

        // Configura la animación de desvanecimiento (fade-in)
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 1000L // Duración de la animación de desvanecimiento (1 segundo)
        holder.itemView.startAnimation(fadeIn)

        holder.itemView.setOnClickListener {
            mostrarDialogo(mapa)
        }
    }

    override fun getItemCount(): Int {
        return mapas.size
    }

    private fun mostrarDialogo(mapa: Mapa) {
        // Crea y muestra el diálogo personalizado con la información del mapa
        val mapaDialog = MapaDialog(context, mapa)
        mapaDialog.show()
    }
}
