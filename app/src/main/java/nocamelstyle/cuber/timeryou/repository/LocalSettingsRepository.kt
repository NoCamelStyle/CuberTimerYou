package nocamelstyle.cuber.timeryou.repository

import android.content.Context
import androidx.core.content.edit
import nocamelstyle.cuber.timeryou.dataset.defaultCategoryNames
import nocamelstyle.cuber.timeryou.dataset.defaultCubeNames

class LocalSettingsRepository(context: Context) {

    private val pref = context.getSharedPreferences("local-settings", Context.MODE_PRIVATE)

    var cubeName: String
        get() = pref.getString("cubeName", null) ?: defaultCubeNames.first()
        set(value) = pref.edit { putString("cubeName", value) }
    var cubeCategory: String
        get() = pref.getString("cubeCategory", null) ?: defaultCategoryNames.first()
        set(value) = pref.edit { putString("cubeCategory", value) }

}