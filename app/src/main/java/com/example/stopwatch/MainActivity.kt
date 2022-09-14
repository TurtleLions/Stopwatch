package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "MainActivity"
    }
    private lateinit var startButton: Button
    private lateinit var resetButton: Button
    private lateinit var timer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wireWidgets()
        startstop()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    private fun wireWidgets(){
        startButton = findViewById(R.id.button_main_start)
        resetButton = findViewById(R.id.button_main_reset)
        timer = findViewById(R.id.chronometer_main_stopwatch)
    }

    private fun startstop(){
        var offset: Long = 0L
        var start = true
        startButton.setOnClickListener {
            if(start){
                startButton.text = "stop"
                start = false
                timer.setBase(SystemClock.elapsedRealtime() + offset)
                timer.start()

            }
            else{
                startButton.text = "start"
                start = true
                offset = timer.getBase() - SystemClock.elapsedRealtime()
                timer.stop()
            }
        }
        resetButton.setOnClickListener {
            timer.setBase(SystemClock.elapsedRealtime())
            offset = 0
            timer.stop()
            startButton.text = "start"
            start=true

        }
    }

}