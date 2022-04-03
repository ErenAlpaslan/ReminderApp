package com.easylife.hobbyreminder.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easylife.hobbyreminder.entity.GeneralError
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {

    protected val _error: MutableLiveData<String?> = MutableLiveData()
    val error: LiveData<String?> = _error

    protected val _showProgress: MutableLiveData<Boolean> = MutableLiveData()
    val showProgress: LiveData<Boolean> = _showProgress

    fun hideError() = viewModelScope.launch {
        _error.postValue(null)
    }

    fun showProgress() = viewModelScope.launch {
        _showProgress.postValue(true)
    }

    fun hideProgress() = viewModelScope.launch {
        _showProgress.postValue(false)
    }

}