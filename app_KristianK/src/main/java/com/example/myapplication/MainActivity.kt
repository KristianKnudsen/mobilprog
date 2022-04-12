package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = (supportFragmentManager.findFragmentById(R.id.foto_nav_host) as NavHostFragment).navController

        val toolbar: Toolbar = findViewById(R.id.foto_top_toolbar)
        setSupportActionBar(toolbar)

        val appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()

        NavigationUI.setupWithNavController(toolbar,navController, appBarConfiguration)
    }
}