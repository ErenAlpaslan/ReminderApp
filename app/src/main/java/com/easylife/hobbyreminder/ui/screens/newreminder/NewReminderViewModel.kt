package com.easylife.hobbyreminder.ui.screens.newreminder

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.easylife.hobbyreminder.base.BaseViewModel
import com.easylife.hobbyreminder.common.persistence.ReminderResult
import com.easylife.hobbyreminder.entity.ReminderConfig
import com.easylife.hobbyreminder.entity.ThemeEntity
import com.easylife.hobbyreminder.ui.repository.ThemeRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewReminderViewModel(
    private val themeRepository: ThemeRepository
): BaseViewModel() {

    private val _themes: MutableLiveData<List<ThemeEntity>?> = MutableLiveData()
    val themes: LiveData<List<ThemeEntity>?> = _themes

    private val _title: MutableLiveData<String?> = MutableLiveData()
    val title: LiveData<String?> = _title

    private val _isSaveEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSaveEnabled: LiveData<Boolean> = _isSaveEnabled

    private val _reminderConfig: MutableLiveData<ReminderConfig> = MutableLiveData()

    private val _isReminderSettingUp: MutableLiveData<Boolean> = MutableLiveData(false)
    val isReminderSettingUp: LiveData<Boolean> = _isReminderSettingUp

    fun onTitleChanged(text: String?) {
        _title.value = text
        if (_reminderConfig.value != null) {
            _reminderConfig.value?.title = text
        }
        controlFields()
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

    fun onReminderConfigChanged(reminderConfig: ReminderConfig) {
        _reminderConfig.value = reminderConfig
        if (_reminderConfig.value != null) {
            _isReminderSettingUp.postValue(true)
        }
        controlFields()
    }

    private fun controlFields() {
        viewModelScope.launch {
            if (!title.value.isNullOrEmpty() && _reminderConfig.value != null) {
                _isSaveEnabled.postValue(true)
            }
        }
    }

    private fun save() {

    }
}