package com.example.newmed.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.newmed.R
import com.example.newmed.databinding.ActivityMainBinding
import com.example.newmed.presentation.fragments.MenuFragment
import com.example.newmed.presentation.interfaces.OnBackInterface

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, MenuFragment(), "Start")
            .commit()
    }

    override fun onBackPressed() {
          val fragment = this.supportFragmentManager.findFragmentById(R.id.frame)
        (fragment as? OnBackInterface)?.onBackPressed()?.not()?.let {
            super.onBackPressed()
        }
    }
}