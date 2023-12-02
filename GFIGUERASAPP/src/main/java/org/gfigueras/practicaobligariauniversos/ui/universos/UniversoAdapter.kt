package org.gfigueras.practicaobligariauniversos.ui.universos

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.model.entities.Universo

/**
 * La clase UniversoAdapter es un adaptador de RecyclerView diseñado para mostrar una lista de universos en una interfaz de usuario. Se utiliza para vincular los datos de la lista de universos con las vistas correspondientes y gestionar las interacciones del usuario, como hacer clic en un elemento de la lista para abrir una nueva actividad.
 * @param context Contexto de la aplicacion
 * @param universos Lista de universos
 * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
 */
class UniversoAdapter(private val context: Context, private val universos: List<Universo>) :
    RecyclerView.Adapter<UniversoAdapter.UniversoViewHolder>() {

    /**
     * Clase interna que representa la vista de un elemento de la lista de universos.
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    class UniversoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversoViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.list_item_universo, parent, false)
        return UniversoViewHolder(view)
    }

    override fun onBindViewHolder(holder: UniversoViewHolder, position: Int) {
        val universo = universos[position]

        Glide.with(context).load(universo.getImagen()).into(holder.imageView)

        holder.nombreTextView.text = universo.getNombre()

        holder.itemView.setOnClickListener {
            //mostrarDialogo(universo)
            Controller.universoSaved = universo
            openActivity(holder.itemView.context)
        }

        // Restablece la vista a su estado original antes de iniciar la animación
        setAnimation(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return universos.size
    }
    /**
     * Aplica una animación a la vista del elemento de la lista.
     * @param view     La vista del elemento de la lista.
     * @param position La posición del elemento en la lista.
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    private fun setAnimation(view: View, position: Int) {
        val slideIn: Animation = AnimationUtils.loadAnimation(context, R.anim.slide)
        view.startAnimation(slideIn)
    }
    /**
     * Abre la actividad UniversoActivity al hacer clic en un elemento de la lista.
     * @param context El contexto de la aplicación.
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    private fun openActivity(context: Context) {
        val intent = Intent(context, UniversoActivity::class.java)
        val options = ActivityOptionsCompat.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out)
        ActivityCompat.startActivity(context as Activity, intent, options.toBundle())
    }
}
