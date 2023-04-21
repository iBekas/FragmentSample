package ru.lesson.fragmentsample.presentation.recycler

//Что такое sealed class сам почитай
sealed class RecyclerEvent {
    //Здесь хранятся все наши события из View(фрагмента)
    object GetItems : RecyclerEvent()
}
