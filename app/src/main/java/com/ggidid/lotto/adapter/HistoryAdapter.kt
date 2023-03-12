package com.ggidid.lotto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ggidid.lotto.R
import com.ggidid.lotto.databinding.FragmentGenerateBinding
import com.ggidid.lotto.databinding.ItemGenNumberBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemGenNumberBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_gen_number,parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}