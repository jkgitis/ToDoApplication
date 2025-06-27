package com.example.todoapplication.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("todo_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val IS_LOGGED_IN = "is_logged_in"
    }

    fun saveLoginState(isLoggedIn: Boolean) {
        prefs.edit().putBoolean(IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun getLoginState(): Boolean {
        return prefs.getBoolean(IS_LOGGED_IN, false)
    }

    fun clearLoginState() {
        prefs.edit().clear().apply()
    }
}
