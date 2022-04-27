package com.example.mythreadskotlin

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MainService(name: String = "") : IntentService(name) {
    override fun onHandleIntent(p0: Intent?) {
        Log.d("@@@", "work MainService")
    }
}