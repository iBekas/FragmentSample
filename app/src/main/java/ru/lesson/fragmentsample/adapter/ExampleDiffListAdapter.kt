package ru.lesson.fragmentsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.lesson.fragmentsample.databinding.ItemRecyclerDiffBinding
import ru.lesson.fragmentsample.model.ExampleModel

class ExampleDiffListAdapter(val clickListener: (String) -> Unit) : ListAdapter<ExampleModel, ExampleDiffViewHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleDiffViewHolder {
        //Устанавливаем макет для одного элемента списка
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerDiffBinding.inflate(inflater, parent, false)
        //Передаем макет в класс держателя
        return ExampleDiffViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ExampleDiffViewHolder, position: Int) {
        //Берем элемент по его позиции
        val exampleModel = getItem(position)
        //.bind функция внутри ExampleDiffViewHolder ожидающая exampleModel, holder экземпляр ExampleDiffViewHolder
        holder.bind(exampleModel)
        //Клик по root(весь макет) возвращает результат clickListener. Можно обработать как внутри ExampleDiffViewHolder, так и здесь
        holder.binding.root.setOnClickListener { clickListener(exampleModel.name) }
    }
}
