package org.gfigueras.practicaobligariauniversos.ui.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        holder.itemView.setOnClickListener {
            mostrarDialogo(mapa.getDescripcion())
        }
    }

    override fun getItemCount(): Int {
        return mapas.size
    }

    private fun mostrarDialogo(descripcion: String) {
        val dialog = AlertDialog.Builder(context)
            .setTitle("Descripción")
            .setMessage(descripcion)
            .setPositiveButton("Cerrar", null)
            .create()
        dialog.show()
        val drawable = ContextCompat.getDrawable(context, R.drawable.background_dialog)
        dialog.window?.setBackgroundDrawable(drawable)
    }
}
