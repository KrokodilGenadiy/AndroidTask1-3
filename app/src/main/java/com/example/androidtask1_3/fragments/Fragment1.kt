package com.example.androidtask1_3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidtask1_3.MainActivity
import com.example.androidtask1_3.databinding.Fragment1Binding

class Fragment1 : Fragment() {
    private var _binding: Fragment1Binding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goNext.setOnClickListener {
            (requireActivity() as MainActivity).navigateForward("1")
        }
        binding.goBack.setOnClickListener {
            (requireActivity() as MainActivity).navigateBack("1")
        }
    }
}