package ru.lesson.fragmentsample.presentation.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.lesson.fragmentsample.databinding.ItemRecyclerBinding
import ru.lesson.fragmentsample.presentation.model.ExampleModel

class ExampleListAdapter(val clickListener: (String) -> Unit) : ListAdapter<ExampleModel, ExampleViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        //Проверка по которой происходит обновление элементов списка
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ExampleModel>() {
            override fun areItemsTheSame(oldItem: ExampleModel, newItem: ExampleModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ExampleModel, newItem: ExampleModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        //Устанавливаем макет для одного элемента списка
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerBinding.inflate(inflater, parent, false)
        //Передаем макет в класс держателя
        return ExampleViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        //Берем элемент по его позиции
        val exampleModel = getItem(position)
        //.bind функция внутри ExampleViewHolder ожидающая exampleModel, holder экземпляр ExampleViewHolder
        holder.bind(exampleModel)
        //Клик по root(весь макет) возвращает результат clickListener. Можно обработать как внутри ExampleViewHolder, так и здесь
        holder.binding.root.setOnClickListener { clickListener(exampleModel.name) }
    }
}
