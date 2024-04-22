package com.apptesting.test.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.apptesting.test.model.AllData

class SessionManager @SuppressLint("CommitPrefEdits") constructor(private val context: Context) {
    private val pref: SharedPreferences =
        context.getSharedPreferences(Const.Key.PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()

    fun saveStringValue(key: String?, value: String?) {
        editor.putString(key, value)
        editor.apply()
    }

    fun getStringValue(key: String): String {
        return pref.getString(key, "")!!
    }

    fun saveBooleanValue(key: String?, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBooleanValue(key: String?): Boolean {
        return pref.getBoolean(key, false)
    }

    fun saveIntValue(key: String?, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    fun getIntValue(key: String?): Int {
        return pref.getInt(key, 0)
    }

    fun clear() {
        editor.clear().apply()
    }


    fun saveSubscriptions(data: List<AllData.SubscriptionPackagesItem?>?) {
        editor.putString(Const.Key.subscriptions, Gson().toJson(data))
        editor.apply()
    }

    fun saveAdmob(data: AllData.AdmobItem) {
        editor.putString(Const.Key.admob, Gson().toJson(data))
        editor.apply()
    }

    fun setPremium(b: Boolean) {
        editor.putBoolean(Const.Key.is_premium, b)
        editor.apply()
    }

    fun getPremium(): Boolean {
        return pref.getBoolean(Const.Key.is_premium, false)

    }


    val subscription: List<AllData.SubscriptionPackagesItem>
        get() {
            val str = pref.getString(Const.Key.subscriptions, "")
            return if (!str.isNullOrEmpty()) {
                Gson().fromJson(
                    str, object : TypeToken<List<AllData.SubscriptionPackagesItem>?>() {}.type
                )
            } else arrayListOf()
        }

    val admob: AllData.AdmobItem?
        get() {
            val str = pref.getString(Const.Key.admob, "")
            return if (!str.isNullOrEmpty()) {
                Gson().fromJson(str, AllData.AdmobItem::class.java)
            } else null
        }
}