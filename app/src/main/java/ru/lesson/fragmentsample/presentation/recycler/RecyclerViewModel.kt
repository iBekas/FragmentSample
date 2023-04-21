package ru.lesson.fragmentsample.presentation.recycler

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.lesson.fragmentsample.data.repository.ItemRepository
import ru.lesson.fragmentsample.data.repository.ItemRepositoryImpl


class RecyclerViewModel(
    //Создаем экземпляр именно интерфейса ItemRepository, но инициализируем классом, который его имплемиентирует,
    // так мы сможешь в любой момент изменить реализацию, почти не затрагивая слой презентации
    private val itemRepository: ItemRepository = ItemRepositoryImpl()
): ViewModel() {

    //Инициализируем нашу LiveData, именно за ее изменениями следит наша View(фрагмент)
    //Про LiveData на самостоятельно изучение
    //В данном случае мы кладет туда RecyclerViewState - наш класс данных, но тут может и просто Booleen или String
    // private val _viewStateLoading = MutableLiveData(Boolean)- как пример
    private val _viewState = MutableLiveData(RecyclerViewState())
    val viewStateObs: LiveData<RecyclerViewState> get() = _viewState
    private var viewState: RecyclerViewState
        get() = _viewState.value!!
        set(value) {
            _viewState.value = value
        }

    fun submitUIEvent(event: RecyclerEvent) {
        handleUIEvent(event)
    }

    private fun handleUIEvent(event: RecyclerEvent) {
        when(event) {
            RecyclerEvent.GetItems -> getListItems()
        }
    }

    private fun getListItems() {
        // Не обращай внимания, этим ты не будешь пользоваться, нужно для задержки в 3 секеунды
        val handler = Handler(Looper.getMainLooper())

        // Запускаем отображение нашей загрузки
        viewState = viewState.copy(isLoading = true)
            handler.postDelayed({
            //ВАЖНО ТОЛЬКО ЭТО - кладем данные в нашу LiveData,
                // в этот момент об этих данных узнает наша View(фрагмент), и так же останавливаем загрузку
            viewState = viewState.copy(itemList = itemRepository.getItems(), isLoading = false)
        }, 3000)

    }

}
