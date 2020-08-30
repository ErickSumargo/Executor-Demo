package com.bael.executor.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bael.executor.demo.ext.textOf
import com.bael.executor.demo.screen.UIArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by ericksumargo on 01/09/20.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHost) as? NavHostFragment

        bottomNav.setupWithNavController(navHostFragment?.navController!!)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            val args = when (item.itemId) {
                R.id.conflatedScreen -> UIArgs(type = textOf(R.string.conflated))
                R.id.queueScreen -> UIArgs(type = textOf(R.string.queue))
                else -> UIArgs(type = textOf(R.string.concurrent))
            }.toBundle()

            navHostFragment.navController.navigate(item.itemId, args)
            true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
