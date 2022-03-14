package com.example.cloudsektest.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import com.example.cloudsektest.MainActivity
import com.example.cloudsektest.MainActivityViewModel
import com.example.cloudsektest.R
import com.example.cloudsektest.databinding.FragmentMainBinding
import com.example.cloudsektest.ui.base.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : ViewBindingFragment<FragmentMainBinding, MainActivityViewModel>() {

    override val viewModel by activityViewModels<MainActivityViewModel>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun String.isValidDomain(): Boolean =
        this.contains("com.")

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun initViews() {
        binding.btnGetAssets.setOnClickListener {
            it.hideKeyboard()
            if (binding.etDomain.text.toString().isValidDomain()) {
                (requireActivity() as MainActivity).addGetAllAssetsFragment(binding.etDomain.text.toString())
            } else {
                showSnackBar(resources.getString(R.string.invalid_package_id))
            }
        }
        binding.btnGetApps.setOnClickListener {
            it.hideKeyboard()
            (requireActivity() as MainActivity).addGetAllAppsFragment()
        }
    }
}