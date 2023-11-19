package com.example.randomfilm.ui.theme

data class Film(
    val title: String,
    val genre: List<String>,
    val year: Int,
    val director: String,
    val actors: List<String>,
    val rating: Double,
    val features: List<String>
)