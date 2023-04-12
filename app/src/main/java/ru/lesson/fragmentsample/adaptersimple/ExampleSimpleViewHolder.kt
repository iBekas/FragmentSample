package ru.lesson.fragmentsample.adaptersimple

import androidx.recyclerview.widget.RecyclerView
import ru.lesson.fragmentsample.databinding.ItemRecyclerSimpleBinding
import ru.lesson.fragmentsample.model.ExampleModel

class ExampleSimpleViewHolder(val binding: ItemRecyclerSimpleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(exampleModel: ExampleModel) {
        //Устанавливаем отображаемые данные
        binding.tvName.text = exampleModel.name
        binding.tvDescription.text = exampleModel.description
    }
}
