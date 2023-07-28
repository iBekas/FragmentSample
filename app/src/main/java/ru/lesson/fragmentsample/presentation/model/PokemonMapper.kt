package ru.lesson.fragmentsample.presentation.model

import ru.lesson.fragmentsample.data.network.model.Pokemon
import ru.lesson.fragmentsample.data.network.model.PokemonDetail
import ru.lesson.fragmentsample.presentation.model.Pokemon as PokemonPresentation
import ru.lesson.fragmentsample.presentation.model.PokemonDetail as PokemonDetailPresentation
import ru.lesson.fragmentsample.presentation.model.Stat as StatPresentation
import ru.lesson.fragmentsample.presentation.model.StatX as StatXPresentation


class PokemonMapper {

    private fun transformPokemonToPresentation(model: Pokemon): PokemonPresentation {

        val number = if(model.url.endsWith("/")) {
            model.url.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            model.url.takeLastWhile { it.isDigit() }
        }
        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"

        return PokemonPresentation(
            name = model.name,
            url = url
        )
    }

    fun transformToPresentation(pokemons: List<Pokemon>): List<PokemonPresentation> {
        return pokemons.map { transformPokemonToPresentation(it) }
    }

    fun transformPokemonDetailToPresentation(model: PokemonDetail): PokemonDetailPresentation {

        val stats = model.stats.map { stat ->
            StatPresentation(
                baseStat = stat.baseStat,
                effort = stat.effort,
                stat = StatXPresentation(name = stat.stat.name, url = stat.stat.url)
            )
        }

        return PokemonDetailPresentation(
            baseExperience = model.baseExperience,
            height = model.height,
            id = model.id,
            locationAreaEncounters = model.locationAreaEncounters,
            name = model.name,
            order = model.order,
            stats = stats
        )
    }

}
