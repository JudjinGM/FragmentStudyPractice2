package com.example.fragmentstudypractice2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragmentstudypractice2.databinding.FragmentANestedBinding

class NestedFragmentA : Fragment() {

    private var _binding: FragmentANestedBinding? = null
    private val binding get() = _binding!!

    private lateinit var text: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        text = (requireActivity() as TextProvider).getText()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentANestedBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            (parentFragment as? SelectPage)?.navigateTo(page = 1)
        }

        binding.nestedFragmentATextView.text = "Это Nested FragmentA \n${this}\n$text"

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("judjin", "onDestroyView NestedFragmentA")
    }

    companion object {
        fun newInstance(text: String) = NestedFragmentA().apply {
            arguments = bundleOf("text" to text)
        }
    }
}