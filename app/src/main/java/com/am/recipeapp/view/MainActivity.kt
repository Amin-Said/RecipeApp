package com.am.recipeapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.am.recipeapp.R
import com.am.recipeapp.databinding.ActivityMainBinding
import com.am.recipeapp.utils.replaceFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setToolbar()
        addRecipesFragment()

    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "All Recipes"
    }

    private fun addRecipesFragment() {
        replaceFragment(
            ListFragment(),
            R.id.fragment_container
        )
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}