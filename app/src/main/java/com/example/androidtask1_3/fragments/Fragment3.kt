package com.example.androidtask1_3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidtask1_3.MainActivity
import com.example.androidtask1_3.databinding.Fragment3Binding

class Fragment3 : Fragment() {
    private var _binding: Fragment3Binding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Fragment3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goNext.setOnClickListener {
            (requireActivity() as MainActivity).navigateForward("3")
        }
        binding.goBack.setOnClickListener {
            (requireActivity() as MainActivity).navigateBack("3")
        }
    }
}