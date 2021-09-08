package com.moonight.mycollections

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import com.moonight.mycollections.view.DashboardView
import com.moonight.mycollections.view.PieChartView

class MainActivity : AppCompatActivity() {
    var currentPos = 0
//    val handler = object : Handler(Looper.getMainLooper()) {
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
//            scale++
//            dashBoard.setCurrentScale(scale)
//            if (scale < 20) {
//                sendEmptyMessageDelayed(0, 500)
//            }
//        }
//    }
//    var scale = 0
//    lateinit var dashBoard: DashboardView
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        dashBoard = findViewById(R.id.dashBoard)
//        handler.sendEmptyMessageDelayed(0, 500)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pieChartView = findViewById<PieChartView>(R.id.customView)
        val percentageList = ArrayList<Float>()
        percentageList.add(0.3f)
        percentageList.add(0.4f)
        percentageList.add(0.1f)
        percentageList.add(0.2f)
        pieChartView.setPercentages(percentageList)
        pieChartView.setOnClickListener {
            currentPos++
            currentPos %= percentageList.size
            pieChartView.setCurrentPos(currentPos)
        }
    }
}