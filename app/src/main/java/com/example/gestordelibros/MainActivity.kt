package com.example.gestordelibros

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = this.getColor(R.color.black)

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(this,drawer,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = "Inicio"
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener{
            val transaction =
                supportFragmentManager.beginTransaction()
            when(it.itemId){
                R.id.nav_item_inicio -> {
                    supportActionBar?.title = "Inicio"

                    val fragment = HomeFragment()
                    // What to do and where to do it
                    transaction.replace(R.id.fragmentContainerView, fragment)

                    }

                R.id.nav_item_añadir -> {
                    supportActionBar?.title = "Añadir"

                    val fragment = AddFragment()
                    // What to do and where to do it
                    transaction.replace(R.id.fragmentContainerView, fragment)
                }

                R.id.editar -> {
                    supportActionBar?.title = "Editar"

                    val fragment = EditFragment()
                    // What to do and where to do it
                    transaction.replace(R.id.fragmentContainerView, fragment)
                    }

                R.id.nav_item_borrar -> {
                    supportActionBar?.title = "Borrar"

                    val fragment = DeleteFragment()
                    // What to do and where to do it
                    transaction.replace(R.id.fragmentContainerView, fragment)

                }
                }
            transaction.addToBackStack(null);
            transaction.commit();


            drawer.closeDrawer(GravityCompat.START)
            true
                }
                }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
                }











