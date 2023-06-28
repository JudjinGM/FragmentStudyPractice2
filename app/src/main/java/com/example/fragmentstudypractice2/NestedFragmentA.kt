package com.example.fragmentstudypractice2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.fragmentstudypractice2.databinding.FragmentANestedBinding

class NestedFragmentA : Fragment() {

    private var _binding: FragmentANestedBinding? = null
    private val binding get() = _binding!!

    private lateinit var text: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentANestedBinding.inflate(inflater, container, false)
        text = (requireActivity() as TextProvider).getText()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_child_container, NestedFragmentB.newInstance(text))
            }
        }
        binding.nestedFragmentATextView.text = "Это Nested FragmentA \n${this}\n$text"
    }

    companion object {
        fun newInstance(text: String) = NestedFragmentA().apply {
            arguments = bundleOf("text" to text)
        }
    }
}