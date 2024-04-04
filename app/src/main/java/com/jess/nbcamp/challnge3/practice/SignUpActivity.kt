package com.jess.nbcamp.challnge3.practice

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.jess.nbcamp.challnge3.R

class SignUpActivity : AppCompatActivity() {

    private val viewModel: SignUpViewModel by viewModels()

    private val etName: EditText by lazy {
        findViewById(R.id.et_name)
    }

    private val etId: EditText by lazy {
        findViewById(R.id.et_id)
    }

    private val etPassword: EditText by lazy {
        findViewById(R.id.et_password)
    }

    private val btButton: Button by lazy {
        findViewById(R.id.bt_sign_up)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

        initView()
        initViewModel()
    }

    private fun initView() {
        btButton.setOnClickListener {
            viewModel.onClickSignUp(
                etName.text.toString(),
                etId.text.toString(),
                etPassword.text.toString()
            )
        }

        etName.addTextChangedListener {

        }

        etId.addTextChangedListener {

        }

        etPassword.addTextChangedListener {

        }
        
        etName.setOnFocusChangeListener { view, b ->

        }

    }

    private fun initViewModel() = with(viewModel) {
        event.observe(this@SignUpActivity) { event ->
            when (event) {
                is SignUpEvent.SignUpSuccess -> {
                    Toast.makeText(
                        this@SignUpActivity,
                        event.member.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}