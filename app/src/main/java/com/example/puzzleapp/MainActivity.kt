package com.example.puzzleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lightsView = findViewById<LightsView>(R.id.lightsview)

        val button = findViewById<Button>(R.id.reset)
        button.setOnClickListener {
            lightsView.model?.reset()
            lightsView.invalidate()
        }
    }
}