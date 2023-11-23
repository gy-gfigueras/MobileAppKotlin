package org.gfigueras.practicaobligatorio.ui.slideshow

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import org.gfigueras.practicaobligariauniversos.MainActivity
import org.gfigueras.practicaobligariauniversos.R
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController
import org.gfigueras.practicaobligariauniversos.databinding.FragmentSlideshowBinding
import org.w3c.dom.Text

class SlideshowFragment : Fragment() {
    var username:TextView?                  = null
    var email:TextView?                     = null
    var userType:TextView?                  = null
    var changeButton:Button?                = null
    var actualPasswd:TextInputLayout?       = null
    var newPasswd: TextInputLayout?         = null
    var newPasswdAuth: TextInputLayout?     = null
    var aceptButton:Button?                 = null
    var controlador:IController?            = null
    var isElementsVisible                   = false
    var menuMapas:Spinner?                  = null


    companion object{
        val IMAGE_REQUEST_CODE = 100
    }

    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        (activity as? MainActivity)?.setToolbarHamburgerIcon(R.drawable.menu)


        username        = binding.username
        email           = binding.email
        userType        = binding.userType
        changeButton    = binding.changePasswd
        actualPasswd    = binding.txtLayoutPasswordOld
        newPasswd       = binding.txtLayoutPasswordNew
        newPasswdAuth   = binding.txtLayoutPasswordNewAuth
        aceptButton     = binding.acceptPasswd
        menuMapas       = binding.spinner


        controlador = Controller(requireContext())

        username!!.text = "Username: ${Controller.userSaved!!.getUsername()}"
        email!!.text    = "Email: ${Controller.userSaved!!.getEmail()}"
        userType!!.text = Controller.userSaved!!.getRole()

        updateElementsVisibility()
        changeButton?.setOnClickListener {
            isElementsVisible = !isElementsVisible
            updateElementsVisibility()
        }

        aceptButton!!.setOnClickListener(){

            lifecycleScope.launch {
                var result = controlador!!.changePassword(Controller.userSaved!!.getUsername(), actualPasswd!!.editText!!.text.toString(), newPasswd!!.editText!!.text.toString(), newPasswdAuth!!.editText!!.text.toString())
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
                            .setMessage("Las contraseñas no coinciden")
                            .setPositiveButton("Aceptar", null)
                            .create()
                        dialog.show()
                    }

                    -2 -> {
                        val dialog = AlertDialog.Builder(requireContext())
                            .setTitle("Error")
                            .setMessage("La contraseña es incorrecta")
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

        var menu:ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(), R.array.Mundos, android.R.layout.simple_spinner_item)

        menu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        menuMapas!!.adapter = menu
        Log.i("CODIGO",Controller.userSaved!!.toString())

        if(Controller.userSaved!!.getFavoriteUniverso() == null){
            menuMapas!!.setSelection(0)

        }else if(Controller.userSaved!!.getFavoriteUniverso()!!.getCodigo() != -1){
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
                        Controller.userSaved!!.setFavoriteUniverso(
                            controlador!!.getUniverso(
                                position
                            )
                        )
                        controlador!!.setUniverseFav(
                            Controller.userSaved!!.getUsername(),
                            actualPasswd!!.editText!!.text.toString(),
                            controlador!!.getUniverso(position)!!
                        )
                    }
                }

                override fun onNothingSelected(parentView: AdapterView<*>) {
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
            newPasswdAuth?.visibility = View.VISIBLE
            aceptButton?.visibility = View.VISIBLE
        } else {
            actualPasswd?.visibility = View.GONE
            newPasswd?.visibility = View.GONE
            newPasswdAuth?.visibility = View.GONE
            aceptButton?.visibility = View.GONE
        }
    }
}