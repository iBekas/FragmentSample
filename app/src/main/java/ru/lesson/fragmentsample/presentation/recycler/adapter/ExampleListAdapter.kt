package ru.lesson.fragmentsample.presentation.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.lesson.fragmentsample.databinding.ItemRecyclerBinding
import ru.lesson.fragmentsample.presentation.model.ExampleModel

class ExampleListAdapter(val clickListener: (ExampleModel) -> Unit) : ListAdapter<ExampleModel, ExampleViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
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
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerBinding.inflate(inflater, parent, false)
        return ExampleViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val exampleModel = getItem(position)
        holder.bind(exampleModel)
        holder.binding.root.setOnClickListener { clickListener(exampleModel) }
    }
}
