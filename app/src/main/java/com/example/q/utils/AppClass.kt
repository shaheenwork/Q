package com.example.q.utils

import android.app.Application
import android.content.Context


class AppClass : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: AppClass? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        val context: Context = applicationContext()
    }


}