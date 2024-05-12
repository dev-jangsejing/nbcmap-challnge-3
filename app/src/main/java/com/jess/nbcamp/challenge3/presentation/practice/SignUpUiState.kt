package com.jess.nbcamp.challenge3.presentation.practice

data class SignUpErrorUiState(
    val name: SignUpValidUiState,
    val emailId: SignUpValidUiState,
    val emailService: SignUpValidUiState,
    val passwordInput: SignUpValidUiState,
    val passwordConfirm: SignUpValidUiState,
    val enabled: Boolean,
) {
    companion object {
        fun init() = SignUpErrorUiState(
            name = SignUpValidUiState.Init,
            emailId = SignUpValidUiState.Init,
            emailService = SignUpValidUiState.Init,
            passwordInput = SignUpValidUiState.Init,
            passwordConfirm = SignUpValidUiState.Init,
            enabled = false
        )
    }
}

sealed interface SignUpValidUiState {
    // 초기 상태
    data object Init : SignUpValidUiState

    // 통과
    data object Valid : SignUpValidUiState

    // 이름
    data object Name : SignUpValidUiState

    // 이메일
    data object EmailBlank : SignUpValidUiState
    data object EmailIncludeAt : SignUpValidUiState

    // 비밀번호 입력
    data object PasswordInputLength : SignUpValidUiState
    data object PasswordInputSpecialCharacters : SignUpValidUiState
    data object PasswordInputUpperCase : SignUpValidUiState

    // 비밀번호 확인
    data object PasswordConfirm : SignUpValidUiState
}
