package org.gfigueras.practicaobligariauniversos.ui.universos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.gfigueras.practicaobligariauniversos.MainActivity
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController
import org.gfigueras.practicaobligariauniversos.databinding.FragmentUniversosBinding
/**
 * Fragmento de Universos, permite al adaptador traer los universos y meterlos mediante codigo a el fragmento de universos
 *
 * Los fragments se inflan en el mainActivity
 * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
 */
class UniversosFragment : Fragment() {

    private var _binding: FragmentUniversosBinding? = null
    private var recyclerView: RecyclerView? = null
    private var controlador: IController? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUniversosBinding.inflate(inflater, container, false)
        val root = binding.root
        controlador = Controller(requireContext())
        recyclerView = binding.recyclerView
        (activity as? MainActivity)?.setToolbarHamburgerIcon(R.drawable.menu)


        recyclerView!!.layoutManager = GridLayoutManager(requireContext(), 1)//1 ES EL NUMERO DE COLUMNAS PARA SEPARAR UNIVERSOS
        recyclerView!!.isNestedScrollingEnabled = false
        recyclerView!!.addItemDecoration(
            GridSpacingItemDecoration(
                1, // NUMERO DE COLUMNAS
                resources.getDimensionPixelSize(R.dimen.grid_spacing),
                true
            )
        )

        val adapter = UniversoAdapter(requireContext(), controlador!!.listUniversos())
        recyclerView!!.adapter = adapter

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
