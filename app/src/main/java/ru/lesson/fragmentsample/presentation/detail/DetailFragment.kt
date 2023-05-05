package ru.lesson.fragmentsample.presentation.detail

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.lesson.fragmentsample.databinding.FragmentDetailBinding
import ru.lesson.fragmentsample.presentation.model.ExampleModel

class DetailFragment : Fragment() {

    companion object {
        private const val KEY = "KEY"

        fun newInstance(item: ExampleModel) = DetailFragment().apply {
            arguments = bundleOf(KEY to item)
        }
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Обрабатываем устаревший метод, если версия андройда на планшете выше, либо равна таргетной для api,
        // используем современный метод
        val item =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) requireArguments().getParcelable(
                KEY,
                ExampleModel::class.java
            ) ?: getEmptyItem() else requireArguments().getParcelable(KEY) ?: getEmptyItem()


        binding.etTitle.setText(item.name)
        viewModel.submitUIEvent(DetailEvent.SaveUserTitle(item.name))
        binding.etDescription.setText(item.description)
        viewModel.submitUIEvent(DetailEvent.SaveUserDescription(item.description))

        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    viewModel.submitUIEvent(DetailEvent.SaveUserTitle(s.toString()))
                }
            }
        })


        binding.etDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    viewModel.submitUIEvent(DetailEvent.SaveUserDescription(s.toString()))
                }
            }
        })

        binding.btSave.setOnClickListener {
            viewModel.submitUIEvent(DetailEvent.SaveItem(item.id))
        }

        //Как сохранится, уходим назад
        viewModel.exit.observe(viewLifecycleOwner) { isExit ->
            if (isExit)
                requireActivity().supportFragmentManager.popBackStackImmediate()
        }

    }

    //Вынеси во вью стейт
    private fun getEmptyItem(): ExampleModel {
        return ExampleModel(
            id = 0,
            name = "",
            description = ""
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
