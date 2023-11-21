package org.gfigueras.practicaobligatorio.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController
import org.gfigueras.practicaobligariauniversos.databinding.FragmentGalleryBinding
import org.gfigueras.practicaobligariauniversos.model.entities.Universo

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private var controlador: IController? = null
    private var universeInput: EditText? = null
    private var search: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        controlador = Controller(requireContext())

        universeInput = binding.universeInput.editText
        search = binding.universeSearch
        val controller: IController = Controller(requireContext())

        search!!.setOnClickListener{
            var universo: Universo = controlador!!.getUniverso(universeInput!!.text.toString())!!
            val dialog = AlertDialog.Builder(requireContext()).setTitle(universo.getNombre()).setMessage(universo.getDescripcion()).setPositiveButton("Aceptar", null).create().show()

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
