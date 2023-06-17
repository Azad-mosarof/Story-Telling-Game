package com.example.storytellinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.storytellinggame.databinding.ActivityUserNameBinding

class UserName : AppCompatActivity() {

    private lateinit var binding: ActivityUserNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserNameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}