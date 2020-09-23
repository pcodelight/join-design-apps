package com.pcodelight.joindesign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pcodelight.joindesign.model.RawMaterial
import com.pcodelight.joindesign.model.Store
import com.pcodelight.joindesign.repo.MaterialRepository
import com.pcodelight.joindesign.repo.StoreRepository
import com.pcodelight.joindesign.service.ApiCallback

class DashboardViewModel(
    private val storeRepo: StoreRepository,
    private val materialRepo: MaterialRepository
) :
    ViewModel() {
    private var _materialResponse = MutableLiveData<List<RawMaterial>>()
    private var _additionalPageResponse = MutableLiveData<List<RawMaterial>>()
    private var _isLoading = MutableLiveData<Boolean>()
    private var _isLoadMore = MutableLiveData<Boolean>()
    private var _error = MutableLiveData<String>()

    private var _isLoadStore = MutableLiveData<Boolean>()
    private var _storeResponse = MutableLiveData<Store>()
    private var _storeError = MutableLiveData<String>()

    val materialResponse: LiveData<List<RawMaterial>> = _materialResponse
    val additionalPageResponse: LiveData<List<RawMaterial>> = _additionalPageResponse
    val isLoading: LiveData<Boolean> = _isLoading
    val isLoadMore: LiveData<Boolean> = _isLoadMore
    val error: LiveData<String> = _error

    lateinit var selectedStore: Store

    var currentPage: Int = 1
    var keyword: String = ""

    fun getMoreMaterials() {
        _isLoadMore.postValue(true)
        currentPage += 1

        materialRepo.getMaterials(
            selectedStore.uuid,
            keyword,
            currentPage,
            object : ApiCallback<List<RawMaterial>> {
                override fun onSuccess(data: List<RawMaterial>?) {
                    _isLoadMore.postValue(false)
                    _error.postValue("")
                    _additionalPageResponse.postValue(data)
                }

                override fun onError(error: String?) {
                    _isLoadMore.postValue(false)
                    _error.postValue(error)
                }
            }
        )
    }

    fun getRawMaterials(keyword: String) {
        this.currentPage = 1
        this.keyword = keyword

        _isLoading.postValue(true)
        materialRepo.getMaterials(
            selectedStore.uuid,
            keyword,
            currentPage,
            object : ApiCallback<List<RawMaterial>> {
                override fun onSuccess(data: List<RawMaterial>?) {
                    _isLoading.postValue(false)
                    _error.postValue("")
                    _materialResponse.postValue(data)
                }

                override fun onError(error: String?) {
                    _isLoading.postValue(false)
                    _error.postValue(error)
                }
            })
    }

    fun getStoreData() {
        _isLoadStore.postValue(true)

        storeRepo.getStoreDetail(selectedStore.authCode, object : ApiCallback<Store> {
            override fun onSuccess(data: Store?) {
                _isLoadStore.postValue(false)
                _storeError.postValue("")
                _storeResponse.postValue(data)
            }

            override fun onError(error: String?) {
                _isLoadStore.postValue(false)
                _storeError.postValue(error)
            }
        })
    }
}