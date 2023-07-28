package ru.lesson.fragmentsample.presentation.pokemons.pokemonlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import ru.lesson.fragmentsample.app.App
import ru.lesson.fragmentsample.data.network.NetworkRepositoryImpl
import ru.lesson.fragmentsample.presentation.model.Pokemon
import ru.lesson.fragmentsample.util.Resource


class PokemonsListViewModel(
    private val networkRepository: NetworkRepositoryImpl = NetworkRepositoryImpl(App.getPokemonApi())
): ViewModel() {

    private val disposables = CompositeDisposable()

    val pokemons = MutableLiveData<List<Pokemon>>(emptyList())
    val loading = MutableLiveData(false)

    init {
        loadPokemons()
    }

    private fun loadPokemons() {
        networkRepository.getPokemons()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { resource ->
                when (resource) {
                    Resource.Loading -> loading.postValue(true)

                    is Resource.Data -> {
                        pokemons.postValue(resource.data ?: emptyList())
                        loading.postValue(false)
                    }

                    is Resource.Error -> loading.postValue(false)
                }
            }
            .addTo(disposables)
    }

    override fun onCleared() {
        disposables.clear()
    }

}
