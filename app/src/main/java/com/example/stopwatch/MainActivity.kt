package com.example.stopwatch

import android.net.wifi.rtt.CivicLocationKeys.STATE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import com.example.stopwatch.MainActivity.Companion.STATE_TIME

class MainActivity : AppCompatActivity() {

    companion object{
        val TAG = "MainActivity"
        val STATE_TIME = "display time"
        val STATE_START = "is it running"
        val STATE_OFFSET = "offset"
    }
    private lateinit var startButton: Button
    private lateinit var resetButton: Button
    private lateinit var timer: Chronometer
    var offset: Long = 0L
    var displayTime = 0L
    var start = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wireWidgets()

        if(savedInstanceState!=null){
            offset = savedInstanceState.getLong(STATE_OFFSET)
            start=savedInstanceState.getBoolean(STATE_START)
            displayTime=savedInstanceState.getLong(STATE_TIME)
            Log.d(TAG,"displayTime: $displayTime start: $start offset: $offset")
            if(start){
                startButton.text = "start"
                timer.base = SystemClock.elapsedRealtime()-displayTime
                timer.stop()
            }
            else{
                startButton.text = "stop"
                timer.base = SystemClock.elapsedRealtime()-displayTime
                timer.start()
            }



        }

        startstop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if(!start) {
            displayTime = SystemClock.elapsedRealtime() - timer.base
        }
        else{
            timer.setBase(SystemClock.elapsedRealtime() + offset)
            displayTime=(SystemClock.elapsedRealtime()-timer.base)
        }
        Log.d(TAG,"displayTime: $displayTime start: $start offset: $offset")
        outState?.run {
            putLong(STATE_TIME, displayTime)
            putBoolean(STATE_START, start)
            putLong(STATE_OFFSET, offset)
        }
        super.onSaveInstanceState(outState)
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