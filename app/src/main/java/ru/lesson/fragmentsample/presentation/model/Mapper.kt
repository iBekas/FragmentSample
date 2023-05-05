package ru.lesson.fragmentsample.presentation.model


import ru.lesson.fragmentsample.data.db.entity.ExampleEntity
import ru.lesson.fragmentsample.presentation.model.ExampleModel as ExampleModelPresentation

object Mapper {

    private fun transformToPresentation(model: ExampleEntity): ExampleModelPresentation {
        return ExampleModelPresentation(
            id = model.id,
            name = model.name,
            description = model.description
        )
    }

    fun transformToPresentation(task: List<ExampleEntity>): List<ExampleModelPresentation> {
        return task.map { transformToPresentation(it) }
    }

    fun transformToData(model: ExampleModelPresentation): ExampleEntity {
        return ExampleEntity(
            id = model.id,
            name = model.name,
            description = model.description
        )
    }

}
