package org.gfigueras.practicaobligariauniversos.ui.slideshow

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import org.gfigueras.practicaobligariauniversos.MainActivity
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController
import org.gfigueras.practicaobligariauniversos.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private var controlador: IController? = null

    var username: TextView? = null
    var email: TextView? = null
    var userType: TextView? = null
    var changeButton: Button? = null
    var actualPasswd: TextInputLayout? = null
    var newPasswd: TextInputLayout? = null
    var aceptButton: Button? = null
    var isElementsVisible = false
    var menuMapas: Spinner? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root = binding.root
        val mainActivity = requireActivity() as MainActivity
        (activity as? MainActivity)?.setToolbarHamburgerIcon(R.drawable.menu)


        username = binding.username
        email = binding.email
        userType = binding.userType
        changeButton = binding.changePasswd
        actualPasswd = binding.txtLayoutPasswordOld
        newPasswd = binding.txtLayoutPasswordNew
        aceptButton = binding.acceptPasswd
        menuMapas = binding.spinner


        controlador = Controller(requireContext())

        username!!.text = "Username: ${Controller.userSaved!!.getUsername()}"
        email!!.text = "Email: ${Controller.userSaved!!.getEmail()}"
        userType!!.text = Controller.userSaved!!.getRole()

        updateElementsVisibility()
        changeButton?.setOnClickListener {
            isElementsVisible = !isElementsVisible
            updateElementsVisibility()
        }

        aceptButton!!.setOnClickListener() {

            lifecycleScope.launch {
                var result = controlador!!.changePassword(
                    Controller.userSaved!!.getUsername(),
                    actualPasswd!!.editText!!.text.toString(),
                    newPasswd!!.editText!!.text.toString(),
                )
                when (result) {
                    0 -> {
                        val dialog = AlertDialog.Builder(requireContext())
                            .setTitle("Correcto")
                            .setMessage("Contraseña cambiada correctamente")
                            .setPositiveButton("Aceptar", null)
                            .create()
                        dialog.show()
                    }

                    -1 -> {
                        val dialog = AlertDialog.Builder(requireContext())
                            .setTitle("Error")
                            .setMessage("La contraseña actual es incorrecta")
                            .setPositiveButton("Aceptar", null)
                            .create()
                        dialog.show()
                    }

                    -3 -> {
                        val dialog = AlertDialog.Builder(requireContext())
                            .setTitle("Error")
                            .setMessage("No se ha podido establecer")
                            .setPositiveButton("Aceptar", null)
                            .create()
                        dialog.show()
                    }

                    else -> {
                        val dialog = AlertDialog.Builder(requireContext())
                            .setTitle("Error")
                            .setMessage("Error en la base de datos")
                            .setPositiveButton("Aceptar", null)
                            .create()
                        dialog.show()
                    }
                }
            }
        }

        var menu: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(), R.array.Mundos, R.layout.spinner_item
        )

        menu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        menuMapas!!.adapter = menu
        Log.i("CODIGO", Controller.userSaved!!.toString())

        if (Controller.userSaved!!.getFavoriteUniverso() == null) {
            menuMapas!!.setSelection(0)

        } else if (Controller.userSaved!!.getFavoriteUniverso()!!.getCodigo() != -1) {
            menuMapas!!.setSelection(Controller.userSaved!!.getFavoriteUniverso()!!.getCodigo())

        }


        menuMapas!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                lifecycleScope.launch {
                    val newSelectedUniverso = controlador!!.getUniverso(position)
                    if (position == 0) {
                        // Si la opción seleccionada es "None", establecer el universo favorito como null
                        Controller.userSaved!!.setFavoriteUniverso(null)
                        controlador!!.setUniverseFav(Controller.userSaved!!.getUsername(), null)
                    } else {
                        // Si la opción seleccionada no es "None", establecer el nuevo universo seleccionado
                        Controller.userSaved!!.setFavoriteUniverso(newSelectedUniverso)

                        // Actualizar también en la base de datos
                        val result = controlador!!.setUniverseFav(
                            Controller.userSaved!!.getUsername(),
                            Controller.userSaved!!.getFavoriteUniverso()!!
                        )
                        Log.i("BOOLEAN", result.toString())
                    }

                    // Actualizar la vista en org.gfigueras.practicaobligariauniversos.MainActivity
                    mainActivity?.changeBackground()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Aquí puedes agregar código para manejar el caso en el que no se selecciona nada.
            }
        }




        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateElementsVisibility() {
        if (isElementsVisible) {
            actualPasswd?.visibility = View.VISIBLE
            newPasswd?.visibility = View.VISIBLE
            aceptButton?.visibility = View.VISIBLE
        } else {
            actualPasswd?.visibility = View.GONE
            newPasswd?.visibility = View.GONE
            aceptButton?.visibility = View.GONE
        }
    }
}



