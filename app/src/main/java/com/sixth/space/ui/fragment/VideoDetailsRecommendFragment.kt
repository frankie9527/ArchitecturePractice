package com.sixth.space.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import com.sixth.space.databinding.FragmentVideoDetailsRemomendBinding
import com.sixth.space.model.RemoteViewModel

import com.sixth.space.ui.HotListAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.easy.ui.recycler.listener.ItemClickListener

@AndroidEntryPoint
class VideoDetailsRecommendFragment : Fragment(), ItemClickListener {
    lateinit var binding: FragmentVideoDetailsRemomendBinding;
    val viewModel: RemoteViewModel by viewModels()
    lateinit var adapter: HotListAdapter;
    var position: Int = 0;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoDetailsRemomendBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onItemClick(p0: View?, position: Int) {

    }


}





