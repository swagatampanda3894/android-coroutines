package com.example.mycoroutines

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val tag = "MainActivityThraed"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.tv)

        var job = GlobalScope.launch(Dispatchers.IO) {
            repeat(5) {
                delay(1000L)
                Log.d(tag, "HI REPEATING")
                textView.setText("HI")
            }
        }
        runBlocking {
            delay(2000L)
            job.join()
          //  job.cancel()
            Log.d(tag, "Main Thread is Continuing")
        }
//        GlobalScope.launch(Dispatchers.IO) {
//            val networkCall = doNetworkCall()
//            Log.d(tag, "Coroutines says hello from thraed1 " + Thread.currentThread().name)
//
//            withContext(Dispatchers.Main) {
//                Log.d(tag, "Coroutines says hello from thraed2 " + Thread.currentThread().name)
//                textView.setText(networkCall)
//
//                Toast.makeText(
//                    applicationContext,
//                    "Welcome to Kotlin Android!" + networkCall,
//                    Toast.LENGTH_SHORT
//                ).show()
//
//            }
//        }
//        Log.d(tag, "Coroutines says hello from thraed3 " + Thread.currentThread().name)
    }

    suspend fun doNetworkCall(): String {
        delay(3000L)
        return "HI"
    }
}