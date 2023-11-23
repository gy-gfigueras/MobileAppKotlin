package org.gfigueras.practicaobligariauniversos

import android.content.Intent
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.StyleSpan
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.navigation.NavigationView
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController
import org.gfigueras.practicaobligariauniversos.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.lang.reflect.Type

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var navigationView: NavigationView? = null
    private var fondoNav: ImageView? = null
    private var controlador: IController? = null
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        controlador = Controller(this)


        navView.setBackgroundColor(getColor(R.color.nav_background))



        navigationView = findViewById<NavigationView>(R.id.nav_view)
        val menu = navigationView!!.menu
        val typeface = ResourcesCompat.getFont(this, R.font.font_file)



        for(i in 0 until menu.size()){
            val menuItem = menu.getItem(i)
            val title = menuItem.title.toString()

            // Crea un SpannableString para aplicar el tipo de letra
            val spannable = SpannableString(title)
            spannable.setSpan(StyleSpan(Typeface.NORMAL), 0, spannable.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            spannable.setSpan(CustomTypefaceSpan("", typeface), 0, spannable.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

            // Establece el título del elemento del menú con el SpannableString
            menuItem.title = spannable
        }

        fondoNav = navigationView!!.getHeaderView(0).findViewById(R.id.fondoNav)

        Glide.with(this)
            .load(controlador!!.getUniverso(Controller.userSaved!!.getFavoriteUniverso()!!.getCodigo())!!.getImagen())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(fondoNav!!)


        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.appBarMain.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Cambia el color del icono aquí
        binding.appBarMain.toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.light_gold), PorterDuff.Mode.SRC_ATOP)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val headerView = navView.getHeaderView(0)

        val username = headerView.findViewById<TextView>(R.id.usernameSaved)
        val email = headerView.findViewById<TextView>(R.id.emailSaved)
        val role = headerView.findViewById<TextView>(R.id.roleSaved)

        username.text = Controller.userSaved!!.getUsername()
        email.text = Controller.userSaved!!.getEmail()
        role.text = Controller.userSaved!!.getRole().toUpperCase()

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitleTextAppearance(this, R.style.NavigationDrawerTextStyle)

        setSupportActionBar(toolbar)

        // Cambiar el color del icono de la hamburguesa
        val actionBar = supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.menu)  // Reemplaza "ic_menu" con tu propio icono
        actionBar?.setDisplayHomeAsUpEnabled(true)



}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        super.onBackPressed()
        startActivity(loginIntent)
        overridePendingTransition(R.anim.scale_up, R.anim.scale_down)
    }

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

}
