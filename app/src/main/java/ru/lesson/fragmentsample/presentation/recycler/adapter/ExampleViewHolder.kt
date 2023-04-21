package ru.lesson.fragmentsample.presentation.recycler.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.lesson.fragmentsample.data.model.ExampleModel
import ru.lesson.fragmentsample.databinding.ItemRecyclerBinding

class ExampleViewHolder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(exampleModel: ExampleModel) {
        //Устанавливаем отображаемые данные
        binding.tvName.text = exampleModel.name
        binding.tvDescription.text = exampleModel.description
    }
}
