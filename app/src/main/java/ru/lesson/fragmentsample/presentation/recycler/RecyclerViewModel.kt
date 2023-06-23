package ru.lesson.fragmentsample.presentation.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import ru.lesson.fragmentsample.app.App
import ru.lesson.fragmentsample.data.repository.ItemRepository
import ru.lesson.fragmentsample.data.repository.ItemRepositoryImpl
import ru.lesson.fragmentsample.presentation.model.Mapper
import ru.lesson.fragmentsample.util.Resource


class RecyclerViewModel(
    private val itemRepository: ItemRepository = ItemRepositoryImpl(App.getExampleDao())
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _viewState = MutableLiveData(RecyclerViewState())
    val viewStateObs: LiveData<RecyclerViewState> get() = _viewState
    var viewState: RecyclerViewState
        get() = _viewState.value!!
        set(value) {
            _viewState.value = value
        }

    fun submitUIEvent(event: RecyclerEvent) {
        handleUIEvent(event)
    }

    private fun handleUIEvent(event: RecyclerEvent) {
        when (event) {
            RecyclerEvent.GetItems -> getListItems()
            is RecyclerEvent.DeleteItem -> deleteItem(id = event.id)
        }
    }

    private fun getListItems() {

        itemRepository.getItems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                viewState = when (result) {
                    Resource.Loading -> viewState.copy(isLoading = true)

                    is Resource.Data -> viewState.copy(
                        itemList = Mapper.transformToPresentation(result.data),
                        isLoading = false
                    )

                    is Resource.Error -> viewState.copy(isLoading = false, errorText = result.error.message ?: "")
                }
            }
            .addTo(disposables)

    }


    private fun deleteItem(id: Long) {

        itemRepository.deleteExample(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                 when (result) {
                    Resource.Loading -> viewState = viewState.copy(isLoading = true)

                    is Resource.Data -> getListItems()

                    is Resource.Error -> viewState = viewState.copy(isLoading = false, errorText = result.error.message ?: "")
                }
            }
            .addTo(disposables)

    }

    override fun onCleared() {
        disposables.clear()
    }

}
