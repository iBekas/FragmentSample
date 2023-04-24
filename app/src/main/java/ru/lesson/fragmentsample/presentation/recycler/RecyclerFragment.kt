package ru.lesson.fragmentsample.presentation.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.lesson.fragmentsample.R
import ru.lesson.fragmentsample.databinding.FragmentRecyclerBinding
import ru.lesson.fragmentsample.presentation.detail.DetailFragment
import ru.lesson.fragmentsample.presentation.model.ExampleModel
import ru.lesson.fragmentsample.presentation.recycler.adapter.ExampleListAdapter


class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding!!

    //Получаем экземпляр нашей ViewModel. "by lazy" говорит о том, что экземпляр мы получим тогда,
    // когда первый раз вызовем(используем ссылку) viewModel
    private val viewModel: RecyclerViewModel by lazy {
        ViewModelProvider(this)[RecyclerViewModel::class.java]
    }

    private val adapter = ExampleListAdapter { exampleModelName ->
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
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Отправляем во ViewModel Event(событие) о необходимости загрузки данных
        // submitUIEvent - название нам говорит о том же
        // RecyclerEvent.GetItems - во ViewModel мы прописали, что будем делать, когда придет этот ивент
        viewModel.submitUIEvent(RecyclerEvent.GetItems)

        binding.rvFirst.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFirst.adapter = adapter

        //Подписываемся на просмотр изменений в нашей MutableLiveData(RecyclerViewState())
        //Как только будут какие-либо изменения мы получим их здесь
        viewModel.viewStateObs.observe(viewLifecycleOwner) { state ->

            //Устанавливаем видимость элементов в зависимости от загрузки
            binding.loader.isVisible = state.isLoading
            binding.fabAddItem.isVisible = !state.isLoading
            binding.rvFirst.isVisible = !state.isLoading

            adapter.submitList(state.itemList)
        }

        binding.fabAddItem.setOnClickListener {
            //Еще один ивент для ViewModel
            viewModel.submitUIEvent(
                RecyclerEvent.AddItem(
                    ExampleModel(
                        id = -1L,
                        name = "Новый",
                        description = "Новый"
                    )
                )
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
