package com.example.cloudsektest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cloudsektest.data.model.AllAssetsResponse
import com.example.cloudsektest.repository.CloudSEKRepository
import com.example.cloudsektest.repository.Response
import com.example.cloudsektest.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val cloudSEKRepository: CloudSEKRepository,
) : ViewModel() {
    private val _assetsForDomainState =
        MutableStateFlow<ViewState<AllAssetsResponse>>(ViewState.loading())
    val assetsForDomainState: StateFlow<ViewState<AllAssetsResponse>> = _assetsForDomainState

    fun getAllAssets(domain: String) {
        viewModelScope.launch {
            cloudSEKRepository.getAllAssetsForDomain(domain).distinctUntilChanged()
                .collect { result ->
                    when (result) {
                        is Response.Success -> _assetsForDomainState.value =
                            ViewState.Success(result.data)
                        is Response.Error -> _assetsForDomainState.value =
                            ViewState.Failed(result.message)
                    }
                }
        }
    }
}