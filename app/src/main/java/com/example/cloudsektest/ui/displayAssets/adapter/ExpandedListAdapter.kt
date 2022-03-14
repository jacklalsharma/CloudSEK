package com.example.cloudsektest.ui.displayAssets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.cloudsektest.data.model.AssetsUi
import com.example.cloudsektest.databinding.ListItemBinding
import com.example.cloudsektest.databinding.ListSubBinding

class ExpandedListAdapter(private val list: List<String>) :
    RecyclerView.Adapter<ExpandedListAdapter.StringHolder>() {

    class StringHolder(private val listSubBinding: ListSubBinding) :
        RecyclerView.ViewHolder(listSubBinding.root) {
        fun bind(name: String) {
            listSubBinding.listTitle.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringHolder {
        return StringHolder(
            ListSubBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StringHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}