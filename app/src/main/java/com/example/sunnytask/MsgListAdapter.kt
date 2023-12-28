package com.example.sunnytask

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sunnytask.databinding.ItemMsgBinding

class MsgListAdapter(private val context: Context, private val msgArrayList: ArrayList<Message>) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return MsgItem(
            ItemMsgBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return msgArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemView = holder as MsgItem
        itemView.bind(msgArrayList[position])
    }

    inner class MsgItem(private val binding: ItemMsgBinding) : ViewHolder(binding.root) {
        fun bind(msg: Message) {
            binding.tvUserName.text = msg.GlobalUserName
            binding.tvMsg.text = msg.Message
            binding.tvDate.text = msg.DateCreate
        }
    }

}