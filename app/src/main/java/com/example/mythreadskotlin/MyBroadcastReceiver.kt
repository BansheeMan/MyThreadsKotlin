package com.example.mythreadskotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            val extra = it.getStringExtra(KEY_BUNDLE_SERVICE_MESSAGE)
            Log.d("@@@","MyBroadcastReceiver onReceive $extra")
        }
    }

}