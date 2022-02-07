package com.example.newmed.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newmed.R
import com.example.newmed.databinding.ActivityMainBinding
import com.example.newmed.presentation.fragments.MenuFragment

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, MenuFragment(), "Start")
            .commit()
    }
}