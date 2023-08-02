package nocamelstyle.cuber.timeryou.repository

import android.content.Context
import androidx.core.content.edit
import nocamelstyle.cuber.timeryou.dataset.defaultCategoryNames
import nocamelstyle.cuber.timeryou.dataset.defaultCubeNames

class LocalSettingsRepository(context: Context) {

    val pref = context.getSharedPreferences("local-settings", Context.MODE_PRIVATE)

    var history: List<Int> //fixme set to list and room
        get() = pref.getStringSet("history", setOf())!!.map { it.toInt() }
        set(value) = pref.edit { putStringSet("history", value.map { it.toString() }.toSet()) }

    var cubeName: String
        get() = pref.getString("cubeName", null) ?: defaultCubeNames.first()
        set(value) = pref.edit { putString("cubeName", value) }
    var cubeCategory: String
        get() = pref.getString("cubeCategory", null) ?: defaultCategoryNames.first()
        set(value) = pref.edit { putString("cubeCategory", value) }

    fun addTimer(time: Int) {
        val oldHistory = history + time
        history = oldHistory
    }

}