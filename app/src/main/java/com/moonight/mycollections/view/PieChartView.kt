package com.moonight.mycollections.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.moonight.rocket.dp
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

/**
 * @author lixiangyue
 * Date  : 2021/9/8 10:14 AM
 * Description:
 */
class PieChartView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mCenterWidth = 0f
    private var mCenterHeight = 0f
    private var radius = 120f.dp
    private var rectF = RectF()
    private var sweepAngleList = ArrayList<Float>()
    private var randomColorList =
        listOf(Color.BLACK, Color.RED, Color.GRAY, Color.CYAN, Color.GRAY, Color.GREEN)
    private var currentPos = 0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCenterWidth = w / 2f
        mCenterHeight = h / 2f
        rectF.left = -radius
        rectF.right = radius
        rectF.top = -radius
        rectF.bottom = radius
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawArcs(canvas)
    }

    private fun drawArcs(canvas: Canvas) {
        canvas.save()
        canvas.translate(mCenterWidth, mCenterHeight)
        sweepAngleList.forEachIndexed { index, it ->
            paint.color = randomColorList[index]
            if (index == currentPos) {
                val pointF = getCoordinateXY(it / 2, 10f.dp)
                canvas.save()
                canvas.translate(pointF.x, pointF.y)
                canvas.drawArc(rectF, 0f, it, true, paint)
                canvas.restore()
            } else {
                canvas.drawArc(rectF, 0f, it, true, paint)
            }
            canvas.rotate(it)
        }
        canvas.restore()
    }

    private fun getCoordinateXY(degree: Float, radius: Float): PointF {
        val x = radius * cos(Math.toRadians(degree.toDouble()))
        val y = radius * sin(Math.toRadians(degree.toDouble()))
        return PointF(x.toFloat(), y.toFloat())
    }

    fun setCurrentPos(curPos: Int) {
        currentPos = curPos
        invalidate()
    }

    fun setPercentages(percentageList: ArrayList<Float>) {
        sweepAngleList.clear()
        percentageList.forEach {
            sweepAngleList.add(it * 360)
        }
        invalidate()
    }

}