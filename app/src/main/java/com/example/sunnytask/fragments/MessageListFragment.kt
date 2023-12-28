package com.example.sunnytask.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sunnytask.Message
import com.example.sunnytask.MsgList
import com.example.sunnytask.MsgListAdapter
import com.example.sunnytask.databinding.FragmentMsgListBinding
import com.example.sunnytask.showSnackBar
import com.example.sunnytask.utils.CommonFunctions
import com.example.sunnytask.webServices.ApiResponse
import com.example.sunnytask.webServices.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageListFragment : BaseFragment<FragmentMsgListBinding>(FragmentMsgListBinding::inflate) {
    private lateinit var adapter: MsgListAdapter
    private lateinit var viewModel: MainViewModel
    private val msgArrayList = ArrayList<Message>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MsgListAdapter(requireActivity(), msgArrayList)
        binding.rvMsg.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvMsg.adapter = adapter
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.msgListResponse.observe(requireActivity(), Observer {
            if (activity != null)
            when (it) {
                is ApiResponse.Loading -> {
                      CommonFunctions.showProgress(requireActivity())
                }

                is ApiResponse.Success<*> -> {
                    CommonFunctions.dismissProgress()
                    val data = it.data as MsgList
                    msgArrayList.addAll(data.messages)
                    adapter.notifyDataSetChanged()
                }

                is ApiResponse.Error -> {
                    CommonFunctions.dismissProgress()
                    binding.root.showSnackBar(it.errorMessage)
                    // Handle error with response.errorMessage
                }
            }
        })

//        binding.rvMsg.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (!isLoading && !isLastPage) {
//                    val visibleItemCount = recyclerView.layoutManager?.childCount
//                    val totalItemCount = layoutManager.itemCount
//                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
//
//                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
//                        && firstVisibleItemPosition >= 0
//                    ) {
//                        currentPage++
//                        loadMessages()
//                    }
//                }
//            }
//        })
        viewModel.msgList("972585444533",1,50)
    }
}