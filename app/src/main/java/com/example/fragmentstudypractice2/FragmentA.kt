import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragmentstudypractice2.NestedFragmentA
import com.example.fragmentstudypractice2.R
import com.example.fragmentstudypractice2.databinding.FragmentABinding

class FragmentA : Fragment() {
    private var _binding: FragmentABinding? = null
    private val binding get() = _binding!!

    private lateinit var text: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentABinding.inflate(inflater, container, false)
        text = requireArguments().getString("text") ?: ""
        binding.fragmentATextView.text = "Fragment A $text"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction()
            .add(R.id.fragment_child_container, NestedFragmentA.newInstance(text!!))
            .commit()
    }

    companion object {
        fun newInstance(text: String) = FragmentA().apply {
            arguments = bundleOf("text" to text)
        }
    }
}