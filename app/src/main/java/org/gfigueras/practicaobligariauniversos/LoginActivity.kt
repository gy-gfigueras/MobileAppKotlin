package org.gfigueras.practicaobligariauniversos

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import org.gfigueras.practicaobligariauniversos.model.DAOUsers.DAOUsers
import org.gfigueras.practicaobligariauniversos.model.DAOUsers.IDAOUsers

class LoginActivity : AppCompatActivity() {
    private var buttonGithub: ImageButton? = null
    private var buttonInstagram: ImageButton? = null
    private var buttonLinkedin: ImageButton? = null
    private var loginButton: Button? = null
    private var daousers: IDAOUsers? = null
    private var mode: TextView? = null
    private val MODE_LOGIN = 0
    private val MODE_SIGNUP = 1

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
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
        daousers = DAOUsers()


        if (!loginState) {
            findViewById<TextInputLayout>(R.id.txtLayoutEmail).visibility = TextInputLayout.GONE
            loginState = true
        } else {
            findViewById<TextInputLayout>(R.id.txtLayoutEmail).visibility = TextInputLayout.VISIBLE
            this.findViewById<Button>(R.id.loginButton).text = "Sign Up"
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
                    if (daousers!!.login(username.text.toString(), password.text.toString())) {
                        openMain()
                    } else {
                        val dialog = AlertDialog.Builder(this@LoginActivity).setTitle("ERROR").setMessage("Usuario incorrecto").setPositiveButton("Aceptar", null).create().show()
                    }
                } else {
                    // Corregir el bloque del sign-up
                    when (daousers!!.signUp(username.text.toString(), email.text.toString(), password.text.toString())) {
                        0 -> {
                            val dialog = AlertDialog.Builder(this@LoginActivity).setTitle("Correcto").setMessage("Usuario creado correctamente, inicie sesion").setPositiveButton("Aceptar", null).create().show()
                        }
                        1 -> {
                            val dialog = AlertDialog.Builder(this@LoginActivity).setTitle("Incorrecto").setMessage("El nombre de usuario ya existe").setPositiveButton("Aceptar", null).create().show()
                        }
                        2 -> {
                            val dialog = AlertDialog.Builder(this@LoginActivity).setTitle("Incorrecto").setMessage("Revise su correo, o puede que ya exista").setPositiveButton("Aceptar", null).create().show()
                        }
                        else -> {
                            val dialog = AlertDialog.Builder(this@LoginActivity).setTitle("Error").setMessage("Error en la base de datos").setPositiveButton("Aceptar", null).create().show()

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
    }
    fun openMain() {
        Log.i("LoginActivity", "Abriendo MainActivity")
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.scale_up, R.anim.scale_down)

    }

    // Cuando tu actividad se destruye (por ejemplo, en onDestroy), asegúrate de cerrar el cliente
    override fun onDestroy() {
        super.onDestroy()
        daousers?.closeClient()
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
}
