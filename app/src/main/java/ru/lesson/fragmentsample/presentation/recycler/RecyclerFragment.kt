package ru.lesson.fragmentsample.presentation.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.lesson.fragmentsample.R
import ru.lesson.fragmentsample.databinding.FragmentRecyclerBinding
import ru.lesson.fragmentsample.presentation.detail.DetailFragment
import ru.lesson.fragmentsample.presentation.recycler.adapter.ExampleListAdapter


class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecyclerViewModel by lazy {
        ViewModelProvider(this)[RecyclerViewModel::class.java]
    }

    private val adapter = ExampleListAdapter { model ->
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                DetailFragment.newInstance(model)
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

        viewModel.submitUIEvent(RecyclerEvent.GetItems)

        binding.rvFirst.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFirst.adapter = adapter

        viewModel.viewStateObs.observe(viewLifecycleOwner) { state ->
            binding.loader.isVisible = state.isLoading
            binding.fabAddItem.isVisible = !state.isLoading
            binding.rvFirst.isVisible = !state.isLoading

            //Ловим ошибку
            if (state.errorText.isNotBlank())
                Toast.makeText(context, state.errorText, Toast.LENGTH_SHORT).show()

            adapter.submitList(state.itemList)
        }

        binding.fabAddItem.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    DetailFragment.newInstance(viewModel.viewState.getEmptyItem())
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
