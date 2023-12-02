package org.gfigueras.practicaobligariauniversos

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
/**
 * Clase LoadingActivity, es ejecutada despues del inicio de sesion.
 *
 * Tiene un TimeOut de 1,4 segundos, hace una animacion Lottie de login y despues te deriva a al MainActivity
 * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
 */
@Suppress("DEPRECATION")
class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()  // Finaliza la actividad actual
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, 1400)
    }
}
