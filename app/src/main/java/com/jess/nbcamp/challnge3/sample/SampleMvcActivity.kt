package com.jess.nbcamp.challnge3.sample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jess.nbcamp.challnge3.R

class SampleMvcActivity : AppCompatActivity() {

    private val tvTest: TextView by lazy {
        findViewById(R.id.tv_test)
    }

    private val btTest: Button by lazy {
        findViewById(R.id.bt_test)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        btTest.setOnClickListener {
            tvTest.text = "jess"
        }
    }
}