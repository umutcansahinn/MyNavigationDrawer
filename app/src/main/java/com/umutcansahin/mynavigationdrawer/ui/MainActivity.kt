package com.umutcansahin.mynavigationdrawer.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.umutcansahin.mynavigationdrawer.R
import com.umutcansahin.mynavigationdrawer.common.showToast
import com.umutcansahin.mynavigationdrawer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set toolBar
        setSupportActionBar(binding.appBarMain.toolBar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.fragment_container_view)

        //bulunduğu sayfaya göre toolbar'a isim yazma
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navFirstFragment, R.id.navSecondFragment, R.id.navThirdFragment),
            drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        changeHeaderViewItem()
    }

    private fun changeHeaderViewItem() {
        //header icindeki view'lara erisim islemi
        val headerView = binding.navView.getHeaderView(0)
        val headerTextViewName: TextView = headerView.findViewById(R.id.text_view_header_name)
        headerTextViewName.text = "umutcan Şahin"
        val headerTextViewEmail: TextView = headerView.findViewById(R.id.text_view_header_email)
        headerTextViewEmail.text = "umutcan199726@gmail.com"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //default menu item'ları eklemek icin
        menuInflater.inflate(R.menu.default_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //menu item click event atama islemi
        when (item.itemId) {
            R.id.menu_setting -> {
                showToast("Setting")
            }

            R.id.menu_sign_out -> {
                showToast("Sign Out")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_container_view)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}