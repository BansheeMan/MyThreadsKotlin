package com.example.mythreadskotlin

import android.app.IntentService
import android.content.Intent
import android.util.Log
import java.lang.Thread.sleep

class MainService(name: String = "") : IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("@@@", "work MainService")
        intent?.let {
            val extra = it.getStringExtra(KEY_BUNDLE_ACTIVITY_MESSAGE)
            Log.d("@@@", "work MainService $extra")
            sleep(1000L)

            val message = Intent(KEY_WAVE)
            message.putExtra(KEY_BUNDLE_SERVICE_MESSAGE, "Дарова, я Сервиса WHAT'S UP?")
            sendBroadcast(message)
            //LocalBroadcastManager.getInstance(this).sendBroadcast(message)
        }
    }
}