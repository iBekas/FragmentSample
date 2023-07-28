package ru.lesson.fragmentsample.data.network.model

import com.google.gson.annotations.SerializedName


data class PokemonDetail(
    @SerializedName("base_experience")
    val baseExperience: Int,
    val height: Int,
    val id: Int,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    val name: String,
    val order: Int,
    val stats: List<Stat>,
)

data class Stat(
    @SerializedName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: StatX
)

data class StatX(
    val name: String,
    val url: String
)
