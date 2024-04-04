package com.jess.nbcamp.challnge3.practice

sealed interface SignUpEvent {

    data class SignUpSuccess(
        val member: SignUpMember,

    ) : SignUpEvent
}