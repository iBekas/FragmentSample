package ru.lesson.fragmentsample.adaptersimple

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.lesson.fragmentsample.R
import ru.lesson.fragmentsample.databinding.ItemRecyclerSimpleBinding
import ru.lesson.fragmentsample.model.ExampleModel

class ExampleSimpleListAdapter(
    //На вход принимается класс имплементирующий внутренний интерфейс данного адаптера
    private val listener: Listener,
) : RecyclerView.Adapter<ExampleSimpleViewHolder>() {

    interface Listener {
        //В данном случае будем обрабатывать клик через интерфейс
        fun onCLick(exampleModelName: String)
    }

    private var list: List<ExampleModel> = emptyList()

    fun setData(list: List<ExampleModel>) {
        this.list = list
        //После установки листа, для того чтобы отобразились изменения нужно обновить адаптер
        //Посмотри почему notifyDataSetChanged() выделен желтым, потом объяснишь
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleSimpleViewHolder {
        //Устанавливаем макет для одного элемента списка, способ немного отличается от первого варианта с diff
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_simple, parent, false)
        return ExampleSimpleViewHolder(ItemRecyclerSimpleBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ExampleSimpleViewHolder, position: Int) {
        //Берем элемент по его позиции
        val exampleModel = list[position]
        //.bind функция внутри ExampleSimpleViewHolder ожидающая exampleModel, holder экземпляр ExampleSimpleViewHolder
        holder.bind(list[position])
        //Клик по root(весь макет) возвращает результат прописанный в реализации
        //имплементированного интерфейса в функции onCLick.
        // Можно обработать как внутри ExampleSimpleViewHolder, так и здесь
        holder.binding.root.setOnClickListener { listener.onCLick(exampleModel.name) }
    }

    //Обязательно нужно переопределить количество элементов в отображаемом списке
    override fun getItemCount() = list.size
}
