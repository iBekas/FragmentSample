package ru.lesson.fragmentsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.lesson.fragmentsample.adapterdiff.ExampleDiffListAdapter
import ru.lesson.fragmentsample.databinding.FragmentRecyclerDiffBinding
import ru.lesson.fragmentsample.model.ExampleModel


class RecyclerDiffFragment : Fragment() {

    private var _binding: FragmentRecyclerDiffBinding? = null
    private val binding get() = _binding!!

    private val adapter = ExampleDiffListAdapter { exampleModelName ->
        //Действие выполняемое по клику на элемент списка
        Toast.makeText(requireContext(), exampleModelName, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerDiffBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Просто захардкоженный лист с ExampleModel
        val list = listOf(
            ExampleModel(id = 0, name = "Первый", description = "Описание первого"),
            ExampleModel(id = 1, name = "Второй", description = "Описание второго"),
            ExampleModel(id = 2, name = "Третий", description = "Описание третьего"),
            ExampleModel(id = 3, name = "Четвертый", description = "Описание четвертого"),
        )

        //Устанавливаем менеждер адаптера, в данном случае Linear - обычный список
        binding.rvFirst.layoutManager = LinearLayoutManager(requireActivity())

        //Передаем адаптер в наш recycler
        binding.rvFirst.adapter = adapter

        //Добавляем лист элементов в наш адаптер, обновление списка произойдет
        //на основании прописанного в DIFF_CALLBACK в адаптере
        adapter.submitList(list)

        //Обрабатываем клик по кнопке
        binding.buttonFirst.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_fragment,
                    R.anim.exit_fragment,
                    R.anim.enter_fragment_in,
                    R.anim.exit_fragment_out
                )
                .add(
                    R.id.fragment_container,
                    RecyclerSimpleFragment()
                )
                .addToBackStack("")
                .commit()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
