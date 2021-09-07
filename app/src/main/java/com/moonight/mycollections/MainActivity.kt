package com.moonight.mycollections

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.moonight.mycollections.view.DashboardView

class MainActivity : AppCompatActivity() {
    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            scale++
            dashBoard.setCurrentScale(scale)
            if (scale < 20) {
                sendEmptyMessageDelayed(0, 500)
            }
        }
    }
    var scale = 0
    lateinit var dashBoard: DashboardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dashBoard = findViewById(R.id.dashBoard)
        handler.sendEmptyMessageDelayed(0, 500)
    }
}