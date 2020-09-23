package com.pcodelight.joindesign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pcodelight.joindesign.model.RawMaterial
import com.pcodelight.joindesign.repo.MaterialRepository
import com.pcodelight.joindesign.service.ApiCallback

class MaterialDetailViewModel(val repo: MaterialRepository): ViewModel() {
    private var _isLoading = MutableLiveData<Boolean>()
    private var _rawMaterialData = MutableLiveData<RawMaterial>()
    private var _error = MutableLiveData<String>()

    val isLoading: LiveData<Boolean> = _isLoading
    val rawMaterialData: LiveData<RawMaterial> = _rawMaterialData
    val error: LiveData<String> = _error

    var rawMaterialId: String = ""

    fun getRawMaterialDetail() {
        _isLoading.postValue(true)

        repo.getMaterial(rawMaterialId, object: ApiCallback<RawMaterial> {
            override fun onSuccess(data: RawMaterial?) {
                _isLoading.postValue(false)
                _error.postValue("")
                _rawMaterialData.postValue(data)

            }

            override fun onError(error: String?) {
                _isLoading.postValue(false)
                _error.postValue(error)
            }
        })
    }
}