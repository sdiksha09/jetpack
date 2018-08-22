package com.example.shipra.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.shipra.jetpack.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    lateinit var drawer:DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val host= supportFragmentManager.findFragmentById(R.id.the_fragment) as NavHostFragment? ?:return
        val navController =host.navController

        drawer=findViewById(R.id.container)

        NavigationUI.setupActionBarWithNavController(this,navController,drawer)



        }

    override fun onSupportNavigateUp(): Boolean {

        return NavigationUI.navigateUp(drawer, Navigation.findNavController(this,R.id.the_fragment))


    }
    }

