package org.gfigueras.practicaobligariauniversos

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController
import org.gfigueras.practicaobligariauniversos.model.DAOUsers.DAOUsers
import org.gfigueras.practicaobligariauniversos.model.DAOUsers.IDAOUsers
import org.gfigueras.practicaobligariauniversos.model.utiles.Tokenizer


class LoginActivity : AppCompatActivity() {
    private var buttonGithub: ImageButton? = null
    private var buttonInstagram: ImageButton? = null
    private var buttonLinkedin: ImageButton? = null
    private var loginButton: Button? = null
    private var controlador: IController? = null
    private var mode: TextView? = null
    private val MODE_LOGIN = 0
    private val MODE_SIGNUP = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var loginState: Boolean = false
        findViewById<EditText>(R.id.txtUsername).text.clear()
        findViewById<EditText>(R.id.txtPassword).text.clear()

        // Inicialización de vistas
        buttonGithub = findViewById(R.id.githubButton)
        buttonInstagram = findViewById(R.id.instagramButton)
        buttonLinkedin = findViewById(R.id.linkedinButton)
        loginButton = findViewById(R.id.loginButton)
        mode = findViewById(R.id.stateText)
        controlador = Controller(this)

        if (!loginState) {
            findViewById<TextInputLayout>(R.id.txtLayoutEmail).visibility = TextInputLayout.GONE
            loginState = true
        } else {
            findViewById<TextInputLayout>(R.id.txtLayoutEmail).visibility = TextInputLayout.VISIBLE
            findViewById<Button>(R.id.loginButton).text = "Sign Up"
            loginState = false
        }

        mode!!.setOnClickListener {
            loginState = !loginState
            if (loginState) {
                updateUIForLogin()
            } else {
                updateUIForSignUp()
            }
        }

        loginButton!!.setOnClickListener {
            lifecycleScope.launch {
                val username: EditText = findViewById(R.id.txtUsername)
                val password: EditText = findViewById(R.id.txtPassword)
                val email: EditText = findViewById(R.id.txtEmail)

                if (loginState) {
                    if (controlador!!.login(username.text.toString(), password.text.toString())) {
                        Tokenizer.initialize(this@LoginActivity)
                        Controller.userSaved = Tokenizer.tokenizar(controlador!!.getUser(username.text.toString(),password.text.toString()!!))
                        Log.i("USER", Controller.userSaved.toString())
                        showLoadingScreen()
                    } else {
                        val dialog = AlertDialog.Builder(this@LoginActivity)
                            .setTitle("ERROR")
                            .setMessage("Usuario incorrecto")
                            .setPositiveButton("Aceptar", null)
                            .create()
                            .show()
                    }
                } else {
                    // Corregir el bloque del sign-up
                    when (controlador!!.signUp(username.text.toString(), email.text.toString(), password.text.toString())) {
                        0 -> {
                            val dialog = AlertDialog.Builder(this@LoginActivity)
                                .setTitle("Correcto")
                                .setMessage("Usuario creado correctamente, inicie sesión")
                                .setPositiveButton("Aceptar", null)
                                .create()
                                .show()
                        }
                        1 -> {
                            val dialog = AlertDialog.Builder(this@LoginActivity)
                                .setTitle("Incorrecto")
                                .setMessage("El nombre de usuario ya existe")
                                .setPositiveButton("Aceptar", null)
                                .create()
                                .show()
                        }
                        2 -> {
                            val dialog = AlertDialog.Builder(this@LoginActivity)
                                .setTitle("Incorrecto")
                                .setMessage("Revise su correo, o puede que ya exista")
                                .setPositiveButton("Aceptar", null)
                                .create()
                                .show()
                        }
                        else -> {
                            val dialog = AlertDialog.Builder(this@LoginActivity)
                                .setTitle("Error")
                                .setMessage("Error en la base de datos")
                                .setPositiveButton("Aceptar", null)
                                .create()
                                .show()
                        }
                    }
                }
            }
        }

        // Configuración de acciones para los botones de redes sociales
        buttonGithub!!.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(it.context, R.anim.button_click))
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://github.com/gfigueras03"))
            startActivity(browserIntent)
        }

        buttonLinkedin!!.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(it.context, R.anim.button_click))
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.linkedin.com/in/guillermo-figueras-jim%C3%A9nez-b2997a240/")
            )
            startActivity(browserIntent)
        }
        buttonInstagram!!.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(it.context, R.anim.button_click))
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/guiillee_.03/?next=%2F"))
            startActivity(browserIntent)
        }

        val rootView = findViewById<View>(R.id.activityLogin) // Reemplaza con el ID de tu layout principal
        rootView.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusedView = currentFocus

        if (currentFocusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
    override fun onBackPressed() {


        val builder = android.app.AlertDialog.Builder(this)
        builder.setMessage("¿Estás seguro de que quieres salir?")
        builder.setPositiveButton("Sí") { _, _ ->
            finish()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            // Si el usuario cancela, cierra el cuadro de diálogo
            dialog.dismiss()
        }
        val dialog = builder.create().show()

        // super.onBackPressed()

    }

    private fun updateUIForLogin() {
        findViewById<TextInputLayout>(R.id.txtLayoutEmail).visibility = TextInputLayout.GONE
        findViewById<Button>(R.id.loginButton).text = "Login"
        findViewById<TextView>(R.id.stateText).text = "Registrarse"
    }

    private fun updateUIForSignUp() {
        findViewById<TextInputLayout>(R.id.txtLayoutEmail).visibility = TextInputLayout.VISIBLE
        findViewById<Button>(R.id.loginButton).text = "Registrarse"
        findViewById<TextView>(R.id.stateText).text = "Iniciar Sesión"
    }

    private fun showLoadingScreen() {
        val loadingIntent = Intent(this, LoadingActivity::class.java)
        startActivity(loadingIntent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

}
