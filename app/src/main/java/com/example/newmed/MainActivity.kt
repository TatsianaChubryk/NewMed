package com.example.newmed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newmed.databinding.ActivityMainBinding
import com.example.newmed.fragments.MenuFragment

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