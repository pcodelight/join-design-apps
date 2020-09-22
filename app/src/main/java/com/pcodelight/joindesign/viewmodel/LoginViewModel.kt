package com.pcodelight.joindesign.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pcodelight.joindesign.repo.AuthRepository
import com.pcodelight.joindesign.model.AuthData
import com.pcodelight.joindesign.response.AuthDataResponse
import com.pcodelight.joindesign.service.ApiCallback

class LoginViewModel(private val repo: AuthRepository) : ViewModel() {
    private var _errorMsg = MutableLiveData<String>()
    private var _isLoading = MutableLiveData<Boolean>()
    private var _authData = MutableLiveData<AuthData?>()

    val errorMsg: LiveData<String> = _errorMsg
    val isLoading: LiveData<Boolean> = _isLoading
    val authData: LiveData<AuthData?> = _authData

    fun getAuth(username: String, password: String) {
        _isLoading.postValue(true)

        repo.getAuth(username, password, object: ApiCallback<AuthData> {
            override fun onSuccess(data: AuthData?) {
                _isLoading.postValue(false)
                _errorMsg.postValue("")
                _authData.postValue(data)
            }

            override fun onError(error: String?) {
                _isLoading.postValue(false)
                _errorMsg.postValue(error)
            }
        })
    }
}