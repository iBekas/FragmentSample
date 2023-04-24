package ru.lesson.fragmentsample.presentation.recycler

import ru.lesson.fragmentsample.presentation.model.ExampleModel

//Что такое sealed class сам почитай
sealed class RecyclerEvent {
    //Здесь хранятся все наши события из View(фрагмента)

    //Если нам не требуются входные данные используем object
    object GetItems : RecyclerEvent()

    class AddItem(val item: ExampleModel) : RecyclerEvent()
}
