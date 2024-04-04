package com.jess.nbcamp.challnge3.practice

data class SignUpMember(
    val name: String,
    val id: String,
    val pass: String,
)

data class Location(
    val latitude: Float,
    val longitude: Float,
)