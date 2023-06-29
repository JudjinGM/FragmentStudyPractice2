package com.example.fragmentstudypractice2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragmentstudypractice2.databinding.FragmentBNestedBinding

class NestedFragmentB : Fragment() {

    private var _binding: FragmentBNestedBinding? = null
    private val binding get() = _binding!!
    private lateinit var text: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        text = (requireActivity() as TextProvider).getText()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBNestedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            (parentFragment as? SelectPage)?.navigateTo(page = 0)
        }

        binding.nestedFragmentBTextView.text = "Это Nested FragmentB \n${this}\n$text"
    }

    companion object {
        fun newInstance(text: String) = NestedFragmentB().apply {
            arguments = bundleOf("text" to text)
        }
    }
}