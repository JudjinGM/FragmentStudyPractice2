package com.example.fragmentstudypractice2

import FragmentA
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentstudypractice2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TextProvider {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("judjin", "OnCreate Activity")

        if (savedInstanceState == null) {
            Log.d("judjin", "supportFragmentManager.beginTransaction()")

            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, FragmentA.newInstance(getText()))
                .setReorderingAllowed(true)
                .commit()
        }
    }

    override fun getText(): String {
        return "Some text!"
    }
}