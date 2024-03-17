package com.sixth.space.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sixth.space.R
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.databinding.FragmentTiktokCommentBinding
import com.sixth.space.model.RemoteViewModel
import com.sixth.space.network.Resource
import com.sixth.space.network.error.NETWORK_EMPTY
import com.sixth.space.network.error.NO_INTERNET_CONNECTION
import com.sixth.space.ui.adapter.HotAndVideoAdapter
import com.sixth.space.uitls.observe
import org.various.player.utils.UiUtils


/**
 * @author: Frankie
 * @Date: 2024/3/16
 * @Description:
 */
class TiktokCommentFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentTiktokCommentBinding;
    lateinit var adapter: HotAndVideoAdapter
    lateinit var viewModel: RemoteViewModel;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTiktokCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, com.sixth.space.R.style.TransparentBottomSheetStyle);

    }

    override fun onStart() {
        super.onStart()
        val h = (UiUtils.getWindowHeight() * (2 / 3.0)).toInt();
        if (dialog != null) {
            val bottomSheet: View =
                dialog!!.findViewById(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet.getLayoutParams().height = h;
        }
        val parent = requireView().parent as View
        val params = parent.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior
        val bottomSheetBehavior = behavior as BottomSheetBehavior<*>
        bottomSheetBehavior.maxHeight = h
        bottomSheetBehavior.peekHeight = h

    }

    fun initView() {
        observe(viewModel.recipesReplyData, ::handleRecipesData)
        binding.recycler.layoutManager = LinearLayoutManager(context);
        adapter = HotAndVideoAdapter();
        binding.recycler.adapter = adapter;
    }


    //note that :cant the same name as setViewModel
    fun setTikTokViewModel(viewModel: RemoteViewModel) {
        this.viewModel = viewModel;
    }

    fun fetchComment(id: String) {
        viewModel.fetchReplyComment(id)
    }

    @SuppressLint("SetTextI18n")
    fun handleRecipesData(status: Resource<List<VideoInfo>>) {
        when (status) {
            is Resource.Loading -> {
                binding.controlLayout.showLoading()
            }

            is Resource.Success -> status.data?.let {
                binding.controlLayout.hideLoading()
                val data = status.data;
                binding.tvCommentCount.text = data.size.toString() +" "+resources.getString(R.string.comments)
                adapter.setData(data);
            }

            is Resource.DataError -> {
                if (status.errorCode == NO_INTERNET_CONNECTION) {
                    binding.controlLayout.showNoNet();
                    return
                }
                if (status.errorCode == NETWORK_EMPTY) {
                    binding.controlLayout.showEmpty();
                    return
                }
                binding.controlLayout.showError();
            }
        }
    }
}