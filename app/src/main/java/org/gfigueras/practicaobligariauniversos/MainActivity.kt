package org.gfigueras.practicaobligariauniversos

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Bundle
import android.service.controls.Control
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.StyleSpan
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController
import org.gfigueras.practicaobligariauniversos.databinding.ActivityMainBinding
import org.gfigueras.practicaobligariauniversos.model.utiles.Tokenizer
import org.gfigueras.practicaobligariauniversos.ui.slideshow.SlideshowFragment
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var navigationView: NavigationView? = null
    private var fondoNav: ImageView? = null
    private var controlador: IController? = null
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configuración inicial de la actividad
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        // Inicialización de variables y vistas
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        controlador = Controller(this)

        // Configuración del fondo de la barra de navegación
        navView.setBackgroundColor(getColor(R.color.nav_background))

        // Obtener referencias a vistas
        navigationView = findViewById(R.id.nav_view)
        ResourcesCompat.getFont(this, R.font.font_file)

        fondoNav = navigationView!!.getHeaderView(0).findViewById(R.id.fondoNav)

        // Configuración del botón de navegación
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.appBarMain.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Cambia el color del icono de la hamburguesa
        binding.appBarMain.toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.light_gold), PorterDuff.Mode.SRC_ATOP)

        // Configuración del controlador de navegación y de la barra de aplicación
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val headerView = navView.getHeaderView(0)

        // Obtener referencias a vistas del encabezado
        val username = headerView.findViewById<TextView>(R.id.usernameSaved)
        val email = headerView.findViewById<TextView>(R.id.emailSaved)
        val role = headerView.findViewById<TextView>(R.id.roleSaved)

        // Actualizar datos del encabezado
        username.text = Controller.userSaved?.getUsername()
        email.text = Controller.userSaved?.getEmail()
        role.text = Controller.userSaved?.getRole()?.toUpperCase()

        //ACTIVA USUARIOS SI TU ROL ES ADMIN
        val menu = navView.menu
        val usersItem = menu.findItem(R.id.nav_users)

        if (Controller.userSaved!!.getRole() != "ADMIN") {
            usersItem.isVisible = false
        }else{
            usersItem.isVisible = true


        }

        // Configuración de la barra de navegación
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_users
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Configuración de la barra de herramientas
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitleTextAppearance(this, R.style.NavigationDrawerTextStyle)
        setSupportActionBar(toolbar)

        // Cambiar el color del icono de la hamburguesa
        val actionBar = supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.menu)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        // Personalización de elementos del menú
        customizeMenuItems()
        lifecycleScope.launch {
            val usersString = controlador!!.getUsers()

            if (!usersString.isNullOrEmpty()) {
                Controller.usuarios = Tokenizer.tokenizarUsers(usersString)
                Log.i("USERS", usersString)
            } else {
                // Manejar el caso en el que getUsers() devuelve null o una cadena vacía
                Log.e("ERROR", "La cadena de usuarios es nula o vacía.")
            }
        }

    }
    //METHODS
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,LoginActivity::class.java))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()

    }

    // Clase interna para aplicar un tipo de letra personalizado
    private class CustomTypefaceSpan(family: String?, private val newType: Typeface?) :
        StyleSpan(Typeface.NORMAL) {
        private val newTypeface: Typeface = newType ?: Typeface.DEFAULT

        override fun updateDrawState(ds: TextPaint) {
            applyCustomTypeFace(ds, newTypeface)
        }

        override fun updateMeasureState(paint: TextPaint) {
            applyCustomTypeFace(paint, newTypeface)
        }

        private fun applyCustomTypeFace(paint: Paint, tf: Typeface) {
            val oldStyle: Int
            val old = paint.typeface
            oldStyle = old?.style ?: 0
            val fake = oldStyle and tf.style.inv()

            if (fake and Typeface.BOLD != 0) {
                paint.isFakeBoldText = true
            }

            if (fake and Typeface.ITALIC != 0) {
                paint.textSkewX = -0.25f
            }

            paint.typeface = tf
        }
    }

    fun setToolbarHamburgerIcon(iconResId: Int) {
        val actionBar = supportActionBar
        actionBar?.setHomeAsUpIndicator(iconResId)
    }

    // Método para personalizar los elementos del menú de navegación
    private fun customizeMenuItems() {
        val menu = navigationView!!.menu
        val typeface = ResourcesCompat.getFont(this, R.font.font_file)

        for (i in 0 until menu.size()) {
            val menuItem = menu.getItem(i)
            val title = menuItem.title.toString()

            val spannable = SpannableString(title)
            spannable.setSpan(StyleSpan(Typeface.NORMAL), 0, spannable.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            spannable.setSpan(CustomTypefaceSpan("", typeface), 0, spannable.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

            menuItem.title = spannable
            changeBackground()
        }
    }

    // Método para manejar el cambio de fondo
    fun changeBackground() {
        try {
            // Verifica si el universo favorito del usuario está en el rango de 1 a 10
            if (Controller.userSaved?.getFavoriteUniverso()?.getCodigo() in 1..10) {
                Glide.with(this)
                    .load(controlador?.getUniverso(Controller.userSaved?.getFavoriteUniverso()?.getCodigo()!!)!!.getImagen())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(fondoNav!!)
                applyDarknessFilter(fondoNav!!, 0.7f)
            } else if (Controller.userSaved?.getFavoriteUniverso() == null) {
                fondoNav!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_nav))
                applyDarknessFilter(fondoNav!!, 0.0f)
            }
        } catch (e: NullPointerException) {
            fondoNav!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_nav))!!
            applyDarknessFilter(fondoNav!!, 0.0f)
        }
    }

    // Método para aplicar un filtro de oscuridad a una imagen
    fun applyDarknessFilter(imageView: ImageView, darknessIntensity: Float) {
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0f) // Desatura la imagen para hacerla completamente en escala de grises
        colorMatrix.setScale(1f - darknessIntensity, 1f - darknessIntensity, 1f - darknessIntensity, 1f)

        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        imageView.colorFilter = colorFilter
    }
}