package ru.lesson.fragmentsample.data.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.lesson.fragmentsample.data.network.model.PokemonDetail
import ru.lesson.fragmentsample.data.network.model.Result


interface PokeApi {

    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Observable<Result>

    @GET("pokemon/{name}")
    fun getPokemonInfo(
        @Path("name") name: String
    ): Observable<PokemonDetail>

}
