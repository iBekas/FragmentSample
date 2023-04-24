package ru.lesson.fragmentsample.presentation.model


import ru.lesson.fragmentsample.data.model.ExampleModel
//переназначаем имя ExampleModel из слоя презентации, чтобы не было путаницы
import ru.lesson.fragmentsample.presentation.model.ExampleModel as ExampleModelPresentation

//В каждом слое мы используем свою модель,
// поэтому нам нужно конвертировать ExampleModel из data в ExampleModel из presentation слоя
object Mapper {

    private fun transformToPresentation(model: ExampleModel): ExampleModelPresentation {
        return ExampleModelPresentation(
            id = model.id,
            name = model.name,
            description = model.description
        )
    }

    //Трансформировать будем в месте получения данных, т.е. во ViewModel
    fun transformToPresentation(task: List<ExampleModel>): List<ExampleModelPresentation> {
        return task.map { transformToPresentation(it) }
    }

}
