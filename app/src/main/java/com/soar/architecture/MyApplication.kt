package com.soar.architecture

import android.app.Application
import android.content.Context

/**
 * NAME：YONG_
 * Created at: 2023/3/22 15
 * Describe:
 */
class MyApplication : Application(){

    private lateinit var instance:Application

    companion object{
        lateinit var context: Context
        lateinit var instance:Application
    }


    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        instance = this
        context = getApplicationContext()
    }

}