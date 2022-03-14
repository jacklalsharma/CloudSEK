package com.example.cloudsektest.ui.displayAssets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.cloudsektest.data.model.AssetsUi
import com.example.cloudsektest.databinding.ListItemBinding

class StringAdapter(
    private val list: List<AssetsUi>) :
    RecyclerView.Adapter<StringAdapter.StringHolder>() {

    class StringHolder(private val listItemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root) {
        fun bind(assetsUi: AssetsUi, position: Int) {
            listItemBinding.title.text = assetsUi.name
            listItemBinding.ivDropDown.setOnClickListener {
                listItemBinding.ivDropDown.isVisible = false
                listItemBinding.ivDropUp.isVisible = true
                listItemBinding.divider.isVisible = false
                listItemBinding.rvSubList.adapter = ExpandedListAdapter(assetsUi.values)
            }
            listItemBinding.ivDropUp.setOnClickListener {
                listItemBinding.ivDropUp.isVisible = false
                listItemBinding.ivDropDown.isVisible = true
                listItemBinding.divider.isVisible = true
                listItemBinding.rvSubList.adapter = ExpandedListAdapter(listOf())
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringHolder {
        return StringHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StringHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}