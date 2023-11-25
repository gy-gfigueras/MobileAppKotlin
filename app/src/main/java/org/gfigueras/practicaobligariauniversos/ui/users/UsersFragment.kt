package org.gfigueras.practicaobligariauniversos.ui.users

import android.os.Bundle
import android.service.controls.Control
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.gfigueras.practicaobligariauniversos.MainActivity
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController
import org.gfigueras.practicaobligariauniversos.databinding.FragmentUsersBinding

class UsersFragment : Fragment() {
    private var _binding: FragmentUsersBinding? = null
    private var controlador: IController? = null

    private val binding get() = _binding!!
    private var selectedUsernameIndex: Int = 0 // Variable para almacenar el índice seleccionado

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.setToolbarHamburgerIcon(R.drawable.menu)

        // Inicializa nameList antes de agregar elementos
        val nameList: MutableList<String> = mutableListOf()
        controlador = Controller(requireContext())
        // Evita el uso de Controller.usuarios!! que puede resultar en NullPointerException
        Controller.usuarios?.forEach { user ->
            nameList.add(user.getUsername())
        }

        val spinner: Spinner = binding.spinner
        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, nameList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        // Establece el índice seleccionado en el Spinner
        spinner.setSelection(selectedUsernameIndex, false)

        // Maneja la selección en el Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                // Actualiza el índice seleccionado
                selectedUsernameIndex = position
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Se llama cuando no se ha seleccionado nada, si es necesario realizar alguna acción
            }
        }

        binding.botonBorrar.setOnClickListener {
            lifecycleScope.launch {
                val selectedUsername = nameList[selectedUsernameIndex]
                val username = Controller.userSaved?.getUsername()
                if (username != null && controlador != null) {
                   if( controlador!!.deleteUser(username, username.lowercase(), selectedUsername.toString())) {
                       val dialog = AlertDialog.Builder(requireContext())
                           .setTitle("Correcto")
                           .setMessage("Usuario borrado Correctamente")
                           .setPositiveButton("Aceptar", null)
                           .create()
                           .show()

                   }else{
                       val dialog = AlertDialog.Builder(requireContext())
                           .setTitle("Incorrecto")
                           .setMessage("El usuario no ha podido borrarse")
                           .setPositiveButton("Aceptar", null)
                           .create()
                           .show()
                   }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

