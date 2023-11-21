package org.gfigueras.practicaobligatorio.ui.home

import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController
import org.gfigueras.practicaobligariauniversos.databinding.FragmentHomeBinding
import org.gfigueras.practicaobligariauniversos.model.entities.Universo

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var gridLayout: GridLayout? = null
    private var controlador: IController? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        controlador = Controller(requireContext())
        gridLayout = binding.gridlayout
        gridLayout!!.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        gridLayout!!.removeAllViews()

        val screenWidth = resources.displayMetrics.widthPixels
        val itemWidth = ((screenWidth / 2) - 10)

        val handler = Handler(Looper.getMainLooper())

        controlador!!.listUniversos().forEachIndexed { index, u ->
            handler.postDelayed({
                addUniversoToGridLayout(u, itemWidth)
            }, index * 500L) // Ajusta el tiempo de retraso según sea necesario
        }

        return root
    }

    private fun addUniversoToGridLayout(universo: Universo, itemWidth: Int) {
        val layout: GridLayout = GridLayout(requireContext())
        val layoutParams = GridLayout.LayoutParams()
        layoutParams.width = itemWidth
        layout.layoutParams = layoutParams
        layoutParams.setMargins(8, 16, 8, 16)

        layout.rowCount = 2
        layout.columnCount = 1
        layout.setBackgroundColor(resources.getColor(R.color.white))
        layout.setPadding(16, 16, 16, 16)


        layout.setOnClickListener {
            // Al hacer clic en el layout, muestra un diálogo con la descripción
            mostrarDialogo(universo.getDescripcion())
        }

        val shapeDrawable = GradientDrawable()
        shapeDrawable.cornerRadius = 16f // Ajusta el radio según sea necesario
        shapeDrawable.setColor(resources.getColor(R.color.card_color)) // Establece el color de fondo
        layout.background = shapeDrawable

        val imageView: ImageView = ImageView(requireContext())

        // Cargar imágenes según el nombre del universo
        when (universo.getNombre()) {
            "Asgard" ->  imageView.setImageResource(R.drawable.universe_asgard)
            "Vanaheim" ->  imageView.setImageResource(R.drawable.universe_vanaheim)
            "Alfheim" -> imageView.setImageResource(R.drawable.universe_alfheim)
            "Ginnungagap" -> imageView.setImageResource(R.drawable.universe_ginnungagap)
            "Helheim" -> imageView.setImageResource(R.drawable.universe_helheim)
            "Jötunheim" -> imageView.setImageResource(R.drawable.universe_jotunheim)
            "Svartalfheim" -> imageView.setImageResource(R.drawable.universe_svartalfheim)
            "Midgard" -> imageView.setImageResource(R.drawable.universe_midgard)
            "Niflheim" -> imageView.setImageResource(R.drawable.universe_niflheim)
            "Muspelheim" -> imageView.setImageResource(R.drawable.universe_muspelheim)
        }

        val imageParams = GridLayout.LayoutParams()
        imageParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        imageParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        imageView.layoutParams = imageParams
        imageView.adjustViewBounds = true
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER

        val nombre: TextView = TextView(requireContext())
        val nombreParams = GridLayout.LayoutParams()
        nombreParams.setMargins(0, 8, 0, 0)
        nombre.layoutParams = nombreParams
        nombre.textSize = resources.getDimension(R.dimen.text_size)
        nombreParams.setGravity(Gravity.CENTER)

        nombre.text = universo!!.getNombre()
        nombre.setTextColor(resources.getColor(R.color.light_gold))
        val typeface = ResourcesCompat.getFont(requireContext(), R.font.font_file)
        nombre.typeface = typeface

        gridLayout!!.addView(layout)
        layout.addView(imageView)
        layout.addView(nombre)

        val fadeIn = ObjectAnimator.ofFloat(layout, "alpha", 0f, 1f)
        fadeIn.duration = 500
        fadeIn.start()
    }

    private fun mostrarDialogo(descripcion: String) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Descripción")
            .setMessage(descripcion)
            .setPositiveButton("Cerrar", null)
            .create()
        dialog.show()
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.background_dialog)
        dialog.window?.setBackgroundDrawable(drawable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
