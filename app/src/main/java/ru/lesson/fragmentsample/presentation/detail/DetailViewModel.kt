package ru.lesson.fragmentsample.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import ru.lesson.fragmentsample.app.App
import ru.lesson.fragmentsample.data.repository.ItemRepository
import ru.lesson.fragmentsample.data.repository.ItemRepositoryImpl
import ru.lesson.fragmentsample.presentation.model.ExampleModel
import ru.lesson.fragmentsample.presentation.model.Mapper
import ru.lesson.fragmentsample.util.Resource


class DetailViewModel(
    private val itemRepository: ItemRepository = ItemRepositoryImpl(App.getExampleDao())
): ViewModel() {

    private val disposables = CompositeDisposable()

    private val userTitle = MutableLiveData<String>()
    private val userDescription = MutableLiveData<String>()
    val exit = MutableLiveData(false)

    fun submitUIEvent(event: DetailEvent) {
        handleUIEvent(event)
    }

    private fun handleUIEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.SaveUserTitle -> userTitle.postValue(event.text)
            is DetailEvent.SaveUserDescription -> userDescription.postValue(event.text)
            is DetailEvent.SaveItem -> saveNewItem(id = event.id)
        }
    }

    private fun saveNewItem(id: Long) {

        itemRepository.insertExample(Mapper.transformToData(
            ExampleModel(
                id = id,
                name = userTitle.value ?: "",
                description = userDescription.value ?: ""
            )
        ))
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
