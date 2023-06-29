package com.example.fragmentstudypractice2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.fragmentstudypractice2.databinding.FragmentBNestedBinding

class NestedFragmentB : Fragment() {

    private var _binding: FragmentBNestedBinding? = null
    private val binding get() = _binding!!
    private lateinit var text: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBNestedBinding.inflate(inflater, container, false)
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
            parentFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_child_container, NestedFragmentA.newInstance(text)
                )
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit()
        }

        binding.buttonBackB.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.nestedFragmentBTextView.text = "Это Nested FragmentB \n${this}\n$text"
    }

    companion object {
        fun newInstance(text: String) = NestedFragmentB().apply {
            arguments = bundleOf("text" to text)
        }
    }
}