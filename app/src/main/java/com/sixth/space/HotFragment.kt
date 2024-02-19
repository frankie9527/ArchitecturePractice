package com.sixth.space

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sixth.space.databinding.FragmentHomeBinding
import com.sixth.space.databinding.FragmentHotBinding

class HotFragment : Fragment() {
    lateinit var binding: FragmentHotBinding;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotBinding.inflate(inflater, container, false)
        return binding.root
    }
}