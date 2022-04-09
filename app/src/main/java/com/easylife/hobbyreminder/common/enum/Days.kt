package com.easylife.hobbyreminder.common.enum

import androidx.annotation.StringRes
import com.easylife.hobbyreminder.R

enum class Days(val code: Int, @StringRes val shortName: Int) {
    MONDAY(0, R.string.reminder_dialog_monday),
    TUESDAY(1, R.string.reminder_dialog_tuesday),
    WEDNESDAY(2, R.string.reminder_dialog_wednesday),
    THURSDAY(3, R.string.reminder_dialog_thursday),
    FRIDAY(4, R.string.reminder_dialog_friday),
    SATURDAY(5, R.string.reminder_dialog_saturday),
    SUNDAY(6, R.string.reminder_dialog_sunday)
}
