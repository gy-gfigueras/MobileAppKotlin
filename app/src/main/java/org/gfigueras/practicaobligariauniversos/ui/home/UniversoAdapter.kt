package org.gfigueras.practicaobligariauniversos.ui.home

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
import org.gfigueras.practicaobligariauniversos.model.entities.Universo


class UniversoAdapter(private val context: Context, private val universos: List<Universo>) :
    RecyclerView.Adapter<UniversoAdapter.UniversoViewHolder>() {

    class UniversoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
    }

    private var lastAnimatedPosition: Int = 0
        get() = field
        set(value) {
            // No es necesario implementar el setter
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_universo, parent, false)
        return UniversoViewHolder(view)
    }

    override fun onBindViewHolder(holder: UniversoViewHolder, position: Int) {
        val universo = universos[position]

        // Carga la imagen con Glide según el nombre almacenado en la base de datos
        when (universo.getNombre()) {
            "Vanaheim" -> Glide.with(context).load(R.drawable.universe_vanaheim).into(holder.imageView)
            "Asgard" -> Glide.with(context).load(R.drawable.universe_asgard).into(holder.imageView)
            "Alfheim" -> Glide.with(context).load(R.drawable.universe_alfheim).into(holder.imageView)
            "Svartalfheim" -> Glide.with(context).load(R.drawable.universe_svartalfheim).into(holder.imageView)
            "Midgard" -> Glide.with(context).load(R.drawable.universe_midgard).into(holder.imageView)
            "Jötunheim" -> Glide.with(context).load(R.drawable.universe_jotunheim).into(holder.imageView)
            "Muspelheim" -> Glide.with(context).load(R.drawable.universe_muspelheim).into(holder.imageView)
            "Niflheim" -> Glide.with(context).load(R.drawable.universe_niflheim).into(holder.imageView)
            "Helheim" -> Glide.with(context).load(R.drawable.universe_helheim).into(holder.imageView)
            "Ginnungagap" -> Glide.with(context).load(R.drawable.universe_ginnungagap).into(holder.imageView)

            else -> {
                // Puedes manejar un caso predeterminado o mostrar un recurso de imagen predeterminado
                Glide.with(context).load(R.drawable.universe_vanaheim).into(holder.imageView)
            }
        }

        holder.nombreTextView.text = universo.getNombre()

        holder.itemView.setOnClickListener {
            mostrarDialogo(universo.getDescripcion())
        }
    }

    override fun getItemCount(): Int {
        return universos.size
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
