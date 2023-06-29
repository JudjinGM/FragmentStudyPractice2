import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragmentstudypractice2.NestedFragmentA
import com.example.fragmentstudypractice2.PagerAdapter
import com.example.fragmentstudypractice2.R
import com.example.fragmentstudypractice2.SelectPage
import com.example.fragmentstudypractice2.databinding.FragmentABinding
import kotlin.properties.Delegates
import kotlin.random.Random

class FragmentA : Fragment(), SelectPage {
    private var _binding: FragmentABinding? = null
    private val binding get() = _binding!!

    private var backgroundColor by Delegates.notNull<Int>()
    private lateinit var text: String

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        Log.d("judjin", "onAttach $this")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("judjin", "onCreate $this")

        backgroundColor = savedInstanceState?.getInt(KEY_COLOR) ?: randomColor()
        text = requireArguments().getString("text") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        Log.d("judjin", "onCreateView")
        _binding = FragmentABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setBackgroundColor(backgroundColor)

        binding.fragmentATextView.text = "Fragment A $this $text"

        binding.fragmentAButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, newInstance(text)).addToBackStack(null)
                .setReorderingAllowed(true).commit()
        }


        val adapter = PagerAdapter(hostFragment = this)
        binding.pager.adapter = adapter
        Log.d("judjin", "onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("judjin", "onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        Log.d("judjin", "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d("judjin", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("judjin", "onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.d("judjin", "onStop")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("judjin", "onSaveInstanceState")
        outState.putInt(KEY_COLOR, backgroundColor)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("judjin", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("judjin", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("judjin", "onDetach")
    }

    private fun randomColor() = -Random.nextInt(0xFFFFFF)

    companion object {
        const val KEY_COLOR = "key_color"
        fun newInstance(text: String) = FragmentA().apply {
            arguments = bundleOf("text" to text)
        }
    }

    override fun navigateTo(page: Int) {
        binding.pager.currentItem = page
    }
}