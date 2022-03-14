package com.example.cloudsektest.ui.displayApps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cloudsektest.data.model.AllAppsData
import com.example.cloudsektest.databinding.ListAppsBinding

class AppListAdapter(
    private val list: List<AllAppsData>,
    private val onItemClicked: (String, String, View?) -> Unit
) :
    RecyclerView.Adapter<AppListAdapter.StringHolder>() {

    class StringHolder(private val listAppBinding: ListAppsBinding) :
        RecyclerView.ViewHolder(listAppBinding.root) {
        fun bind(packages: AllAppsData, onItemClicked: (String, String, View?) -> Unit) {
            listAppBinding.appPackageId.text = packages.packageId
            listAppBinding.appVersion.text = packages.appVersion
            listAppBinding.appTitle.text = packages.appName
            Glide.with(listAppBinding.root.context)
                .load(packages.icon)
                .into(listAppBinding.icon)
            listAppBinding.root.setOnClickListener {
                onItemClicked.let { onItemClicked->
                    onItemClicked(
                        packages.packageId!!,
                        "appSelected",
                        it
                    )

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringHolder {
        return StringHolder(
            ListAppsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StringHolder, position: Int) {
        holder.bind(list[position], onItemClicked)
    }

    override fun getItemCount(): Int = list.size
}