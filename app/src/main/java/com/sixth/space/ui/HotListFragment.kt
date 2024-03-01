package com.sixth.space.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sixth.space.databinding.FragmentHotListBinding


class HotListFragment() : Fragment() {
    lateinit var binding: FragmentHotListBinding;
    var position: Int = 0;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotListBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun newInstance(position: Int): HotListFragment {
        val args = Bundle()
        val fragment = HotListFragment()
        args.putInt("position", position)
        fragment.arguments = args
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView();
        initData();
    }

    fun initView() {

    }

    fun initData() {
        position = requireArguments().getInt("position")
        binding.tv.setText("hello ${position}")
    }
}
