package org.gfigueras.practicaobligariauniversos

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.gfigueras.practicaobligariauniversos.model.entities.Universo

class UniversoAdapter(private val universos: List<Universo>) : RecyclerView.Adapter<UniversoAdapter.UniversoViewHolder>() {

    class UniversoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val nombre: TextView = itemView.findViewById(R.id.nombreTextView)
        // Otros elementos que puedas necesitar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_universo, parent, false)
        return UniversoViewHolder(view)
    }

    override fun onBindViewHolder(holder: UniversoViewHolder, position: Int) {
        val universo = universos[position]

        // Configura las vistas con los datos del universo
        holder.nombre.text = universo.getNombre()

        when (universo.getNombre()) {
            "Asgard" ->  holder.imageView.setImageResource(R.drawable.universe_asgard)
            "Vanaheim" ->  holder.imageView.setImageResource(R.drawable.universe_vanaheim)
            // ... (configura otras imágenes según sea necesario)
        }

        // Configura el clic en el elemento del RecyclerView
        holder.itemView.setOnClickListener {

