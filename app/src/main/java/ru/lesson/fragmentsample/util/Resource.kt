package ru.lesson.fragmentsample.util


sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Data<T>(val data: T) : Resource<T>()
    data class Error(val error: Throwable) : Resource<Nothing>()

    companion object {
        val Success: Resource<Unit> = Data(Unit)
    }
}
