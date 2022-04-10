package com.easylife.hobbyreminder.ui.screens.newreminder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.easylife.hobbyreminder.R
import com.easylife.hobbyreminder.base.BaseViewModel
import com.easylife.hobbyreminder.common.persistence.ReminderResult
import com.easylife.hobbyreminder.entity.ReminderConfig
import com.easylife.hobbyreminder.entity.ThemeEntity
import kotlinx.coroutines.launch

class NewReminderViewModel(
    private val themeRepository: ThemeRepository,
    private val reminderRepository: ReminderRepository
) : BaseViewModel() {

    private val _themes: MutableLiveData<List<ThemeEntity>?> = MutableLiveData()
    val themes: LiveData<List<ThemeEntity>?> = _themes

    private val _title: MutableLiveData<String?> = MutableLiveData()
    val title: LiveData<String?> = _title

    private val _isSaveEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSaveEnabled: LiveData<Boolean> = _isSaveEnabled

    private val _reminderConfig: MutableLiveData<ReminderConfig> = MutableLiveData()
    private val _selectedTheme: MutableLiveData<ThemeEntity> = MutableLiveData(
        ThemeEntity(
            id = 0,
            color = R.color.red_a100
        )
    )

    private val _isReminderSettingUp: MutableLiveData<Boolean> = MutableLiveData(false)
    val isReminderSettingUp: LiveData<Boolean> = _isReminderSettingUp

    private val _onSaveSucceeded: MutableLiveData<Boolean> = MutableLiveData(false)
    val onSaveSucceeded: LiveData<Boolean> = _onSaveSucceeded

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

    fun onThemeSelected(theme: ThemeEntity) {
        _selectedTheme.value = theme
    }

    fun onSaveClicked() {
        viewModelScope.launch {
            _reminderConfig.value?.theme = _selectedTheme.value
            _reminderConfig.value?.let {
                reminderRepository.insertNewReminder(it).collect { result ->
                    when (result) {
                        is ReminderResult.Error -> _error.postValue(result.message)
                        is ReminderResult.Success -> {
                            _onSaveSucceeded.postValue(true)
                        }
                    }
                }
            }
        }
    }
}