package ru.lesson.fragmentsample.presentation.model


data class PokemonDetail(
    val baseExperience: Int,
    val height: Int,
    val id: Int,
    val locationAreaEncounters: String,
    val name: String,
    val order: Int,
    val stats: List<Stat>,
)

data class Stat(
    val baseStat: Int,
    val effort: Int,
    val stat: StatX
)

data class StatX(
    val name: String,
    val url: String
)
