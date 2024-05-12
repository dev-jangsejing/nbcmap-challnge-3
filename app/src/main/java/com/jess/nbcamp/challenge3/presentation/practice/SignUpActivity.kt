package com.jess.nbcamp.challenge3.presentation.practice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.jess.nbcamp.challenge3.R
import com.jess.nbcamp.challenge3.databinding.SignUpActivityBinding

class SignUpActivity : AppCompatActivity() {

    companion object {

        fun newIntent(
            context: Context,
        ): Intent = Intent(context, SignUpActivity()::class.java)
    }

    private val binding: SignUpActivityBinding by lazy {
        SignUpActivityBinding.inflate(layoutInflater)
    }

    private val viewModel: SignUpViewModel by viewModels()

    private val editTexts
        get() = with(binding) {
            listOf(
                etName,
                etEmailId,
                etEmailService,
                etPasswordInput,
                etPasswordConfirm
            )
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        initViewModel()
    }

    private fun initView() {
        editTexts.forEach { editText ->
            editText.addTextChangedListener {
                editText.checkValidElements()
            }

            editText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus.not()) {
                    editText.checkValidElements()
                }
            }
        }
    }

    private fun initViewModel() = with(viewModel) {

        errorUiState.observe(this@SignUpActivity) { uiState ->
            with(binding) {
                // 이름
                tvNameError.setText(
                    when (uiState.name) {
                        SignUpValidUiState.Name -> R.string.sign_up_name_error
                        else -> R.string.sign_up_pass
                    }
                )

                // 이메일
                tvEmailError.setText(
                    when (uiState.emailId) {
                        SignUpValidUiState.EmailBlank -> R.string.sign_up_email_error_blank
                        SignUpValidUiState.EmailIncludeAt -> R.string.sign_up_email_error_at
                        else -> R.string.sign_up_pass
                    }
                )

                // 비밀번호 입력
                tvPasswordInputError.setText(
                    when (uiState.passwordInput) {
                        SignUpValidUiState.PasswordInputLength -> R.string.sign_up_password_error_length
                        SignUpValidUiState.PasswordInputSpecialCharacters -> R.string.sign_up_password_error_special
                        SignUpValidUiState.PasswordInputUpperCase -> R.string.sign_up_password_error_upper
                        else -> R.string.sign_up_pass
                    }
                )
                tvPasswordInputError.isEnabled =
                    uiState.passwordInput != SignUpValidUiState.Valid

                // 비밀번호 확인
                tvPasswordConfirmError.setText(
                    when (uiState.passwordConfirm) {
                        SignUpValidUiState.PasswordConfirm -> R.string.sign_up_password_confirm_error
                        else -> R.string.sign_up_pass
                    }
                )

                // 버튼
                btConfirm.isEnabled = uiState.enabled
            }
        }

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

    /**
     * 각 항목의 유효성 검사를 처리합니다.
     */
    private fun EditText.checkValidElements() = with(binding) {
        when (this@checkValidElements) {
            etName -> viewModel.checkValidName(etName.text.toString())
            etEmailId -> viewModel.checkValidEmail(etEmailId.text.toString())
            etEmailService -> viewModel.checkValidEmail(etEmailService.text.toString())
            etPasswordInput -> viewModel.checkValidPasswordInput(etPasswordInput.text.toString())
            etPasswordConfirm -> viewModel.checkValidPasswordConfirm(
                etPasswordInput.text.toString(),
                etPasswordConfirm.text.toString(),
            )

            else -> Unit
        }
    }
}