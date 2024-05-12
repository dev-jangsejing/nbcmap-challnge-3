package com.jess.nbcamp.challenge3.presentation.practice

sealed interface SignUpEvent {

    data class SignUpSuccess(
        val member: SignUpMember,

        ) : SignUpEvent
}