package com.example.cloudsektest.ui.displayApps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cloudsektest.MainActivity
import com.example.cloudsektest.MainActivityViewModel
import com.example.cloudsektest.data.model.AllAppsData
import com.example.cloudsektest.databinding.FragmentDisplayAppsBinding
import com.example.cloudsektest.ui.base.ViewBindingFragment
import com.example.cloudsektest.ui.displayApps.adapter.AppListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayAppsFragment :
    ViewBindingFragment<FragmentDisplayAppsBinding, MainActivityViewModel>() {

    override val viewModel by viewModels<MainActivityViewModel>()
    private var onItemClicked = { packageId: String, key: String, view: View? ->
        when (key) {
            "appSelected" -> {
                (requireActivity() as MainActivity).addGetAllAssetsFragment(packageId)
            }
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDisplayAppsBinding =
        FragmentDisplayAppsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllInstalledApps()
    }

    private fun getAllInstalledApps() {
        val packages =
            (requireActivity() as MainActivity).packageManager.getInstalledPackages(0)
        val appsList = mutableListOf<AllAppsData>()
        packages.forEach {
            if (it.versionName != null) {
                appsList.add(
                    AllAppsData(
                        packageId = it.packageName,
                        appName = it.applicationInfo.loadLabel(requireActivity().packageManager)
                            .toString(),
                        appVersion = it.versionName,
                        icon = it.applicationInfo.loadIcon(requireActivity().packageManager)
                    )
                )
            }
        }
        if (appsList.isNotEmpty()) {
            binding.text.isVisible = false
            renderView(appsList.toList())
        }
    }

    private fun renderView(appsList: List<AllAppsData>) {
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvList.adapter = AppListAdapter(appsList, onItemClicked)
        binding.progress.isVisible = false
    }
}