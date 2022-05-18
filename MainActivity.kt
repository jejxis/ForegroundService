package com.example.foregroundservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.foregroundservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {
            val intent = Intent(this, Foreground::class.java)
            ContextCompat.startForegroundService(this, intent)//포어그라운드 서비스 시작
        }
        binding.buttonStop.setOnClickListener {
            val intent = Intent(this, Foreground::class.java)
            stopService(intent)//서비스 종료
        }
    }
}