package bangkit.capstone.core.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferenceHelper @Inject constructor(private val sharedPref: SharedPreferences){

    fun setString(key: String, value: String) {
        with (sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getString(key: String) : String {
        return sharedPref.getString(key, "") ?: ""
    }

    fun isStringExist(key: String): Boolean {
        return sharedPref.getString(key, "") != ""
    }

    fun setBool(key: String, value: Boolean) {
        with(sharedPref.edit()) {
            putBoolean(key, value)
            apply()
        }
    }

    fun getBool(key: String) : Boolean {
        return sharedPref.getBoolean(key, false)
    }
}