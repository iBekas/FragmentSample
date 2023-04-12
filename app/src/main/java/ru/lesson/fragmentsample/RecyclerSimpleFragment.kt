package ru.lesson.fragmentsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.lesson.fragmentsample.adapterdiff.ExampleDiffListAdapter
import ru.lesson.fragmentsample.adaptersimple.ExampleSimpleListAdapter
import ru.lesson.fragmentsample.databinding.FragmentRecyclerSimpleBinding
import ru.lesson.fragmentsample.model.ExampleModel

//Имплементируем интерфейс Listener из нашего адаптера
class RecyclerSimpleFragment : Fragment(), ExampleSimpleListAdapter.Listener {

    private var _binding: FragmentRecyclerSimpleBinding? = null
    private val binding get() = _binding!!

    //Так как RecyclerSimpleFragment имплементирует ExampleSimpleListAdapter.Listener,
    //он является слушателем(listener), поэтому вставляем this
    private val adapter = ExampleSimpleListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerSimpleBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Просто захардкоженный лист с ExampleModel
        val list = listOf(
            ExampleModel(id = 0, name = "Василий", description = "Геннадьевич Пупкин"),
            ExampleModel(id = 1, name = "Виктор", description = "Алексеевич Радченко"),
            ExampleModel(id = 2, name = "Харитон", description = "Аккакьвич Макаренко"),
            ExampleModel(id = 3, name = "Платон", description = "Герасимович Дартаньянов"),
        )

        //Устанавливаем менеждер адаптера, в данном случае Grid - плитки
        binding.rvSecond.layoutManager = GridLayoutManager(requireActivity(), 2)

        //Передаем адаптер в наш recycler
        binding.rvSecond.adapter = adapter

        //Добавляем лист элементов в наш адаптер через функцию setData в ExampleSimpleListAdapter
        adapter.setData(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Реализация функции onCLick из интерфейса
    override fun onCLick(exampleModelName: String) {
        Toast.makeText(requireContext(), exampleModelName, Toast.LENGTH_SHORT).show()
    }
}
