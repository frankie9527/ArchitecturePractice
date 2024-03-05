package com.sixth.space.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.sixth.space.base.Constant

import com.sixth.space.databinding.FragmentVideoDetailsCommentBinding
import com.sixth.space.uitls.LogUtils


import org.easy.ui.recycler.listener.ItemClickListener

class TiktokFragment : Fragment(), ItemClickListener {
    lateinit var binding: FragmentVideoDetailsCommentBinding;
    var position: Int = 0;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoDetailsCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun newInstance(position: Int): TiktokFragment {
        val args = Bundle()
        val fragment = TiktokFragment()
        args.putInt(Constant.HOT_POSITION, position)
        fragment.arguments = args
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView();

    }

    fun initView() {
        val position = requireArguments().getInt(Constant.HOT_POSITION)
        binding.tv.text = "this  is the " + position + "position";
    }

    override fun onItemClick(p0: View?, position: Int) {

    }


}





