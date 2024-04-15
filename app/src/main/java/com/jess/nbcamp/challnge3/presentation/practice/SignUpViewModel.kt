package com.jess.nbcamp.challnge3.presentation.practice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jess.nbcamp.challnge3.presentation.practice.SignUpValidExtension.includeAt
import com.jess.nbcamp.challnge3.presentation.practice.SignUpValidExtension.includeSpecialCharacters
import com.jess.nbcamp.challnge3.presentation.practice.SignUpValidExtension.includeUpperCase

class SignUpViewModel : ViewModel() {

    // user
    private val _errorUiState: MutableLiveData<SignUpErrorUiState> =
        MutableLiveData(SignUpErrorUiState.init())
    val errorUiState: LiveData<SignUpErrorUiState> get() = _errorUiState

    private val _event: MutableLiveData<SignUpEvent> = MutableLiveData()
    val event: LiveData<SignUpEvent> get() = _event

    fun checkValidName(text: String) {
        _errorUiState.value = errorUiState.value?.copy(
            name = if (text.isBlank()) {
                SignUpValidUiState.Name
            } else {
                SignUpValidUiState.Valid
            }
        )
    }

    fun checkValidEmail(text: String) {
        _errorUiState.value = errorUiState.value?.copy(
            emailId = when {
                text.isBlank() -> SignUpValidUiState.EmailBlank
                text.includeAt() -> SignUpValidUiState.EmailIncludeAt
                else -> SignUpValidUiState.Valid
            }
        )
    }

    fun checkValidPasswordInput(text: String) {
        _errorUiState.value = errorUiState.value?.copy(
            passwordInput = when {
                text.length < 10 -> SignUpValidUiState.PasswordInputLength

                text.includeSpecialCharacters().not() ->
                    SignUpValidUiState.PasswordInputSpecialCharacters

                text.includeUpperCase().not() -> SignUpValidUiState.PasswordInputUpperCase

                else -> SignUpValidUiState.Valid
            }
        )
    }

    fun checkValidPasswordConfirm(
        text: String,
        confirm: String
    ) {
        _errorUiState.value = errorUiState.value?.copy(
            passwordConfirm = if (text != confirm) {
                SignUpValidUiState.PasswordConfirm
            } else {
                SignUpValidUiState.Valid
            }
        )
    }

    private fun isConfirmButtonEnable() = errorUiState.value?.let { state ->
        _errorUiState.value?.copy(
            enabled = state.name == SignUpValidUiState.Valid
                    && state.emailId == SignUpValidUiState.Valid
                    && state.emailService == SignUpValidUiState.Valid
                    && state.passwordInput == SignUpValidUiState.Valid
                    && state.passwordConfirm == SignUpValidUiState.Valid
        )
    }
}