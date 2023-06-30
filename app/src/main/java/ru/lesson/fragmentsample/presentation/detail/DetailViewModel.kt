package ru.lesson.fragmentsample.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import ru.lesson.fragmentsample.app.App
import ru.lesson.fragmentsample.data.repository.ItemRepository
import ru.lesson.fragmentsample.data.repository.ItemRepositoryImpl
import ru.lesson.fragmentsample.presentation.composecomponents.FIRST_THEME
import ru.lesson.fragmentsample.presentation.composecomponents.THEME_CODE
import ru.lesson.fragmentsample.presentation.model.ExampleModel
import ru.lesson.fragmentsample.presentation.model.Mapper
import ru.lesson.fragmentsample.util.Resource

class DetailViewModel(
    private val itemRepository: ItemRepository = ItemRepositoryImpl(App.getExampleDao())
): ViewModel() {

    private val disposables = CompositeDisposable()

    val exampleModel = MutableLiveData<ExampleModel>()

    val currentTheme = MutableLiveData(FIRST_THEME)
    val exit = MutableLiveData(false)

    init {
        currentTheme.postValue(App.getSettings().getInt(THEME_CODE, FIRST_THEME))
    }

    fun submitUIEvent(event: DetailEvent) {
        handleUIEvent(event)
    }

    private fun handleUIEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.SetItem -> exampleModel.postValue((event.item))
            is DetailEvent.SaveItem -> saveNewItem(id = event.id)
        }
    }

    private fun saveNewItem(id: Long) {

        itemRepository.insertExample(Mapper.transformToData( exampleModel.value!!.copy(id = id)))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { resource ->
                when (resource) {
                    Resource.Loading -> { }

                    is Resource.Data -> exit.postValue(true)

                    is Resource.Error -> { }
                }
            }
            .addTo(disposables)

    }

    override fun onCleared() {
        disposables.clear()
    }

}
