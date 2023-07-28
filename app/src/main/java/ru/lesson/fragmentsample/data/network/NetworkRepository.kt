package ru.lesson.fragmentsample.data.network

import io.reactivex.Observable
import ru.lesson.fragmentsample.presentation.model.Pokemon
import ru.lesson.fragmentsample.presentation.model.PokemonDetail
import ru.lesson.fragmentsample.util.Resource


interface NetworkRepository {

    fun getPokemons(): Observable<Resource<List<Pokemon>>>

    fun getPokemonDetails(name: String): Observable<Resource<PokemonDetail>>

}
