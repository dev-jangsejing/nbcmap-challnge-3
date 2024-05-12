package com.jess.nbcamp.challenge3.presentation.sample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jess.nbcamp.challenge3.R

class SampleMvvmActivity : AppCompatActivity() {

    private val tvTest: TextView by lazy {
        findViewById(R.id.tv_test)
    }

    private val btTest: Button by lazy {
        findViewById(R.id.bt_test)
    }

    private val viewModel: SampleMvvmViewModel by lazy {
        ViewModelProvider(this)[SampleMvvmViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        initView()
        initViewModel()

    }

    private fun initView() {
        btTest.setOnClickListener {
            viewModel.onClickTest("jess")
        }
    }

    private fun initViewModel() {
        viewModel.event.observe(this@SampleMvvmActivity) {
            tvTest.text = it
        }
    }

}