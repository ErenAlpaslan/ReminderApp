package com.easylife.hobbyreminder.ui.screens.newreminder

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.easylife.hobbyreminder.base.BaseViewModel
import com.easylife.hobbyreminder.common.persistence.ReminderResult
import com.easylife.hobbyreminder.entity.ThemeEntity
import com.easylife.hobbyreminder.ui.repository.ThemeRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewReminderViewModel(
    private val themeRepository: ThemeRepository
): BaseViewModel() {

    private val _themes: MutableLiveData<List<ThemeEntity>?> = MutableLiveData()
    val themes: LiveData<List<ThemeEntity>?> = _themes

    private val title: MutableLiveData<String> = MutableLiveData()

    private val _isSaveEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val isSaveEnabled: LiveData<Boolean> = _isSaveEnabled

    fun onTitleChanged(title: String?) {
        this.title.postValue(title)
    }

    fun getThemes() {
        viewModelScope.launch {
            themeRepository.getThemes().collect {
                when (it) {
                    is ReminderResult.Error -> _error.postValue(it.message)
                    is ReminderResult.Success -> {
                        _themes.postValue(it.data)
                    }
                }
            }
        }
    }

}