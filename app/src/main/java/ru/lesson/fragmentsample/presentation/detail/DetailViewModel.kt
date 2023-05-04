package ru.lesson.fragmentsample.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class DetailViewModel: ViewModel() {

    val userText = MutableLiveData<String>()

    fun submitUIEvent(event: DetailEvent) {
        handleUIEvent(event)
    }

    private fun handleUIEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.SaveUserText -> userText.postValue(event.text)
        }
    }

}
