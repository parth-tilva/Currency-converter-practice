package com.example.currencyconverterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.versionedparcelable.ParcelField
import com.example.currencyconverterapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}