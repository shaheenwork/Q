package com.example.q.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManger {

    companion object {
        private var sp: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null

        private fun getInstance(context: Context): SharedPreferences {
            synchronized(this) {
                if (sp ==null) {
                    sp = context.getSharedPreferences("SharedPreferencesFile", Context.MODE_PRIVATE)
                }
                return sp!!
            }
        }

        fun putLong( key: String, value: Long) {
            editor = getInstance(AppClass.applicationContext()).edit()
            editor!!.putLong(key, value)
            editor!!.apply()
        }

        fun getLong( key: String, default: Long): Long {
            return getInstance(AppClass.applicationContext()).getLong(key, default)
        }

        fun putString( key: String, value: String) {
            editor = getInstance(AppClass.applicationContext()).edit()
            editor!!.putString(key, value)
            editor!!.apply()
        }

        fun getString( key: String, default: String): String {
            return getInstance(AppClass.applicationContext()).getString(key, default)!!
        }

        fun putInt( key: String, value: Int) {
            editor = getInstance(AppClass.applicationContext()).edit()
            editor!!.putInt(key, value)
            editor!!.apply()
        }

        fun getInt( key: String, default: Int): Int {
            return getInstance(AppClass.applicationContext()).getInt(key, default)
        }
    }

}