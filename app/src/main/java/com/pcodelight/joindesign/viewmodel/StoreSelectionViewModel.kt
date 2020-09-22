package com.pcodelight.joindesign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pcodelight.joindesign.model.Store
import com.pcodelight.joindesign.repo.StoreRepository
import com.pcodelight.joindesign.service.ApiCallback

class StoreSelectionViewModel(private val repo: StoreRepository) : ViewModel() {
    private var _listStore = MutableLiveData<List<Store>>()
    private var _isLoading = MutableLiveData<Boolean>()
    private var _error = MutableLiveData<String>()

    val listStore: LiveData<List<Store>> = _listStore
    val isLoading: LiveData<Boolean> = _isLoading
    val error: LiveData<String> = _error
    fun getStores() {
        _isLoading.postValue(true)

        repo.getStores(object: ApiCallback<List<Store>> {
            override fun onSuccess(data: List<Store>?) {
                _isLoading.postValue(false)
                _error.postValue("")
                _listStore.postValue(data)
            }

            override fun onError(error: String?) {
                _isLoading.postValue(false)
                _error.postValue(error)
                _listStore.postValue(listOf())
            }
        })
    }
}