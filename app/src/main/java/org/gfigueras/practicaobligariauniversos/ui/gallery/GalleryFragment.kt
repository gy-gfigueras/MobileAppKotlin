package org.gfigueras.practicaobligariauniversos.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.gfigueras.practicaobligariauniversos.MainActivity
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController
import org.gfigueras.practicaobligariauniversos.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private var recyclerView: RecyclerView? = null
    private var controlador: IController? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        controlador = Controller(requireContext())
        recyclerView = binding.recyclerView

        (activity as? MainActivity)?.setToolbarHamburgerIcon(R.drawable.menu)


        // Configura el RecyclerView con un GridLayoutManager y un adaptador
        recyclerView!!.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView!!.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                resources.getDimensionPixelSize(R.dimen.grid_spacing),
                true
            )
        )

        // Configura el adaptador para el RecyclerView con la lista de mapas
        val adapter = MapaAdapter(requireContext(), controlador!!.listMapas())
        recyclerView!!.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
