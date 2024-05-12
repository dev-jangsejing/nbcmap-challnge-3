package com.jess.nbcamp.challenge3.presentation.practice

data class SignUpMember(
    val name: String,
    val id: String,
    val pass: String,
)

data class Location(
    val latitude: Float,
    val longitude: Float,
)