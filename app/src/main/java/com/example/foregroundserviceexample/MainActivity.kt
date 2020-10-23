package com.example.foregroundserviceexample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var startServiceButton: Button
    private lateinit var stopServiceButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startServiceButton = findViewById(R.id.startServiceButton)
        stopServiceButton = findViewById(R.id.stopServiceButton)

        startServiceButton.setOnClickListener { v -> startService( Intent( v.context, ForegroundService::class.java)) }

        stopServiceButton.setOnClickListener{v -> stopService(Intent(v.context, ForegroundService::class.java))}
    }
}