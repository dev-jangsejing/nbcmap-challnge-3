package com.jess.nbcamp.challnge3.practice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jess.nbcamp.challnge3.R

class SignUpActivity : AppCompatActivity() {


    private val viewModel2: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

        val viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
    }
}