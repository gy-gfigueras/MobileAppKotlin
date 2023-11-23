package org.gfigueras.practicaobligariauniversos.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.service.controls.Control
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.gfigueras.practicaobligariauniversos.LoginActivity
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController
import org.gfigueras.practicaobligariauniversos.model.entities.Universo

class UniversoAdapter(private val context: Context, private val universos: List<Universo>) :
    RecyclerView.Adapter<UniversoAdapter.UniversoViewHolder>() {

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

    private fun mostrarDialogo(universo: Universo) {
        val universoDialog: UniverseDialog = UniverseDialog(context, universo)
        universoDialog.show()
    }

    private fun setAnimation(view: View, position: Int) {
        val slideIn: Animation = AnimationUtils.loadAnimation(context, R.anim.slide)
        view.startAnimation(slideIn)
    }

    private fun openActivity(context: Context) {
        val intent = Intent(context, UniversoActivity::class.java)
        val options = ActivityOptionsCompat.makeCustomAnimation(context, R.anim.scale_up, R.anim.scale_down)
        ActivityCompat.startActivity(context as Activity, intent, options.toBundle())
    }
}
