package com.jess.nbcamp.challnge3.presentation.practice

sealed interface SignUpEvent {

    data class SignUpSuccess(
        val member: SignUpMember,

        ) : SignUpEvent
}