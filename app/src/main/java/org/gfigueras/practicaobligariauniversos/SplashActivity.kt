package org.gfigueras.practicaobligariauniversos

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            try {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.scale_up, R.anim.scale_up)

                finish()  // Asegúrate de agregar esta línea para cerrar SplashActivity
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("EROOOOOOOOR", "ERROOOOOOR")
            }
        }, 4800)


    }
}