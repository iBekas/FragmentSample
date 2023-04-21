package ru.lesson.fragmentsample.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.lesson.fragmentsample.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    companion object {
        private const val KEY_NAME = "KEY_NAME"

        fun newInstance(name: String) = DetailFragment().apply {
            arguments = bundleOf(KEY_NAME to name)
        }
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = requireArguments().getString(KEY_NAME) ?: ""

        binding.tvName.text = name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
