package com.example.cloudsektest.ui.displayAssets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cloudsektest.MainActivityViewModel
import com.example.cloudsektest.data.model.AssetsUi
import com.example.cloudsektest.data.model.toAssetsUi
import com.example.cloudsektest.databinding.FragmentDisplayAssetsBinding
import com.example.cloudsektest.ui.base.ViewBindingFragment
import com.example.cloudsektest.ui.displayAssets.adapter.StringAdapter
import com.example.cloudsektest.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DisplayAssetsFragment :
    ViewBindingFragment<FragmentDisplayAssetsBinding, MainActivityViewModel>() {

    companion object {
        fun newInstance(data: String): DisplayAssetsFragment {
            val fragment = DisplayAssetsFragment()
            val args = Bundle()
            args.putString("domain", data)
            fragment.arguments = args
            return fragment
        }
    }

    override val viewModel by viewModels<MainActivityViewModel>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentDisplayAssetsBinding =
        FragmentDisplayAssetsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeGetAllAssetsResponse()
        val domain = arguments?.getString("domain")!!
        viewModel.getAllAssets(domain)
    }

    private fun renderView(assets: List<AssetsUi>) {
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvList.adapter = StringAdapter(assets)
    }

    private fun observeGetAllAssetsResponse() {
        lifecycleScope.launchWhenStarted {
            viewModel.assetsForDomainState.collect {
                when (it) {
                    is ViewState.Empty -> {
                    }
                    is ViewState.Failed -> {
                        showSnackBar(it.message)
                        binding.progress.isVisible = false
                    }
                    is ViewState.Loading -> {
                    }
                    is ViewState.Success -> {
                        binding.progress.isVisible = false
                        if (it.data.assets != null) {
                            var assetUiList = it.data.assets!!.toAssetsUi().filter { it1 ->
                                it1.values.isNotEmpty()
                            }
                            if (assetUiList.isNotEmpty()) {
                                binding.text.isVisible = false
                                renderView(assetUiList)
                            }
                        }
                    }
                }
            }
        }
    }

}