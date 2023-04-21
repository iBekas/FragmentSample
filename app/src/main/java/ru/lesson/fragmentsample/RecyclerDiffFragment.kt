package ru.lesson.fragmentsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.lesson.fragmentsample.adapter.ExampleDiffListAdapter
import ru.lesson.fragmentsample.databinding.FragmentRecyclerDiffBinding
import ru.lesson.fragmentsample.model.ExampleModel


class RecyclerDiffFragment : Fragment() {

    private var _binding: FragmentRecyclerDiffBinding? = null
    private val binding get() = _binding!!

    private val adapter = ExampleDiffListAdapter { exampleModelName ->
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragment_container,
                DetailFragment.newInstance(exampleModelName)
            )
            .addToBackStack("")
            .commit()
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


        binding.rvFirst.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFirst.adapter = adapter
        adapter.submitList(list)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
