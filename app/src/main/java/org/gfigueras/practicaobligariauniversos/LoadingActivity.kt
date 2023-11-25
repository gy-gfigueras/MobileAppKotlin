package org.gfigueras.practicaobligariauniversos

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

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
