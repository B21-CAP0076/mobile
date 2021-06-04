package bangkit.capstone.util

import android.app.Activity
import android.content.Context

object SharedPreferenceHelper {

    fun setString(activity: Activity, key: String, value: String) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getString(activity: Activity, key: String) : String {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString(key, "") ?: ""
    }

    fun isStringExist(activity: Activity, key: String): Boolean {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString(key, "") != ""
    }
}