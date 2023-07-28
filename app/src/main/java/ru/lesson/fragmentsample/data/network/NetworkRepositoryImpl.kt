package ru.lesson.fragmentsample.data.network

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import ru.lesson.fragmentsample.presentation.model.Pokemon
import ru.lesson.fragmentsample.presentation.model.PokemonDetail
import ru.lesson.fragmentsample.presentation.model.PokemonMapper
import ru.lesson.fragmentsample.util.Resource


class NetworkRepositoryImpl(private val api: PokeApi): NetworkRepository{

    private val pokemonMapper = PokemonMapper()

    override fun getPokemons(): Observable<Resource<List<Pokemon>>> {
        return api.getPokemonList(50, 50)
            .map { it.results }
            .map<Resource<List<Pokemon>>> { Resource.Data(pokemonMapper.transformToPresentation(it)) }
            .onErrorReturn { Resource.Error(it) }
            .startWith(Resource.Loading)
            .subscribeOn(Schedulers.io())
    }

    override fun getPokemonDetails(name: String): Observable<Resource<PokemonDetail>> {
        return api.getPokemonInfo(name)
            .map<Resource<PokemonDetail>> { Resource.Data(pokemonMapper.transformPokemonDetailToPresentation(it)) }
            .onErrorReturn { Resource.Error(it) }
            .startWith(Resource.Loading)
            .subscribeOn(Schedulers.io())
    }
}
