package com.sixth.space.ui.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sixth.space.data.HotList
import com.sixth.space.data.Resource
import com.sixth.space.databinding.FragmentHotListBinding
import com.sixth.space.model.TestViewModel
import com.sixth.space.ui.HotListAdapter
import com.sixth.space.uitls.observe
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.easy.ui.recycler.listener.ItemClickListener


class HotListFragment : Fragment(), ItemClickListener {
    lateinit var binding: FragmentHotListBinding;
//     val viewModel: HotViewModel by viewModels()
   val viewModel: TestViewModel by viewModels()
    lateinit var adapter: HotListAdapter;
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

        binding.recycler.layoutManager = LinearLayoutManager(context);
        adapter = HotListAdapter()
        adapter.setItemListener(this);
        binding.recycler.adapter = adapter;



    }

    fun initData() {
        position = requireArguments().getInt("position")
        viewModel.demo();
    }

    private fun handleRecipesList(status: Resource<HotList>) {
        when (status) {
            is Resource.Loading -> hello("Loading")
            is Resource.Success -> status.data?.let { hello("Success") }
            is Resource.DataError -> {
                hello("DataError")
            }
        }
    }

    override fun onItemClick(p0: View?, p1: Int) {
        TODO("Not yet implemented")
    }
}

fun hello(str: String) {
    Log.e("jyh", "str=" + str)
}



