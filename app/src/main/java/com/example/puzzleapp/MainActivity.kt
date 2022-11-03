package com.example.puzzleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val nDefault = 5
    private var model: LightsModel? = null
    private val modelKey = "ModelKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState?.getSerializable(modelKey) != null) {
            model = savedInstanceState.getSerializable(modelKey) as LightsModel
        }

        setContentView(R.layout.activity_main)

        val lightsView = findViewById<LightsView>(R.id.lightsview)
        lightsView.model = getModel()

        val button = findViewById<Button>(R.id.reset)
        button.setOnClickListener {
            lightsView.model?.reset()
            lightsView.invalidate()

            lightsView.model?.getScore()?.let { score -> updateScore(score) }
        }
    }

    fun updateScore(score: Int): Unit {
        val scoreText = findViewById<TextView>(R.id.score)
        scoreText.text = "Score: $score"
        scoreText.invalidate()
    }

    private fun getModel(): LightsModel {
        if (model == null) {
            model = LightsModel(nDefault)
        }
        return model as LightsModel
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(modelKey, getModel())

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        model = savedInstanceState.getSerializable(modelKey) as LightsModel
        super.onRestoreInstanceState(savedInstanceState)
    }
}