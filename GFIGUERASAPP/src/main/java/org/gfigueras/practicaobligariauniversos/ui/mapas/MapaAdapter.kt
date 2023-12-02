package org.gfigueras.practicaobligariauniversos.ui.mapas
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.model.entities.Mapa
/**
 * La clase MapaAdapter es un adaptador de RecyclerView diseñado para mostrar una lista de Mapas en una interfaz de usuario. Se utiliza para vincular los datos de la lista de mapas con las vistas correspondientes y gestionar las interacciones del usuario, como hacer clic en un elemento de la lista para mostrar un dialogo personalizado
 * @see MapaDialog
 * @param context Contexto de la aplicacion
 * @param universos Lista de universos
 * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
 */
class MapaAdapter(private val context: Context, private val mapas: List<Mapa>) :
    RecyclerView.Adapter<MapaAdapter.MapaViewHolder>() {
    /**
     * Clase interna que representa la vista de un elemento de la lista de mapas.
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
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

    /**
     * Devuelve la cantidad de mapas
     * @return Retorna la cantidad de mapas
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    override fun getItemCount(): Int {
        return mapas.size
    }

    /**
     * Llama a la clase MapaDialog, pasandole un mapa con parametro para retornar un dialogo personalizado con dicho mapa
     * @param mapa mapa que pasamos como parametro
     * @see MapaDialog
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    private fun mostrarDialogo(mapa: Mapa) {
        // Crea y muestra el diálogo personalizado con la información del mapa
        val mapaDialog = MapaDialog(context, mapa)
        mapaDialog.show()
    }
}
