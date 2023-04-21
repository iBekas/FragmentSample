package ru.lesson.fragmentsample.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.lesson.fragmentsample.databinding.ItemRecyclerDiffBinding
import ru.lesson.fragmentsample.model.ExampleModel

class ExampleDiffViewHolder(val binding: ItemRecyclerDiffBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(exampleModel: ExampleModel) {
        //Устанавливаем отображаемые данные
        binding.tvName.text = exampleModel.name
        binding.tvDescription.text = exampleModel.description
    }
}
