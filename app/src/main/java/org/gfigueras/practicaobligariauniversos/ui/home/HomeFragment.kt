package org.gfigueras.practicaobligariauniversos.ui.home
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController
import org.gfigueras.practicaobligariauniversos.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var recyclerView: RecyclerView? = null
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
        recyclerView = binding.recyclerView

        // Configura el RecyclerView con un GridLayoutManager y un adaptador
        recyclerView!!.layoutManager = GridLayoutManager(requireContext(), 1)
        recyclerView!!.addItemDecoration(
            GridSpacingItemDecoration(
                1,
                resources.getDimensionPixelSize(R.dimen.grid_spacing),
                true
            )
        )

        // Configura el adaptador para el RecyclerView
        val adapter = UniversoAdapter(requireContext(), controlador!!.listUniversos())
        recyclerView!!.adapter = adapter

        // Añade un ScrollListener para cargar más elementos al hacer scroll
        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Imprime mensajes de registro para verificar si el evento de desplazamiento se está llamando
                Log.d("ScrollListener", "onScrolled: $dy")

                // Comprueba si ha llegado al final del RecyclerView
                if (!recyclerView.canScrollVertically(1)) {
                    // Aquí puedes cargar más elementos o realizar alguna acción al llegar al final
                    Log.d("ScrollListener", "End of list reached!")
                }
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
