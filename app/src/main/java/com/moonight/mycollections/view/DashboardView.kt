package com.moonight.mycollections.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.moonight.rocket.dp

/**
 * @author lixiangyue
 * Date  : 2021/9/6 2:06 PM
 * Description:
 */
class DashboardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var pathEffect: PathDashPathEffect
    private val pathMeasure = PathMeasure()
    private var paintWidth = 3f.dp
    private var radius = 100f.dp
    private var path = Path()
    private var centerWidth = 0f
    private var centerHeight = 0f
    private var rectF = RectF()
    private var openAngle = 120f
    private var starAngle = 0f
    private var sweepAngle = 0f
    private var gap = 0f
    private var markPath = Path()
    private var markWidth = 2f.dp
    private var markHeight = 8f.dp
    private var markSpots = 20
    private var mCurrentScale = 0
    private var pointerLength = 70f.dp

    init {
        paint.style = Paint.Style.STROKE
        paint.color = Color.DKGRAY
        paint.strokeWidth = paintWidth
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerWidth = w / 2f
        centerHeight = h / 2f
        initArcParameters()
        path.reset()
        path.addArc(rectF, starAngle, sweepAngle)
        pathMeasure.setPath(path, false)
        gap = (pathMeasure.length - markWidth) / markSpots
        markPath.addRect(0f, 0f, markWidth, markHeight, Path.Direction.CCW)
        pathEffect = PathDashPathEffect(markPath, gap, 0f, PathDashPathEffect.Style.ROTATE)
    }

    /**
     * 初始化圆弧需要的参数
     */
    private fun initArcParameters() {
        rectF.top = -radius
        rectF.bottom = radius
        rectF.left = -radius
        rectF.right = radius
        starAngle = 90 + openAngle / 2
        sweepAngle = 360 - openAngle
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawMark(canvas)
        drawPointer(canvas)
    }

    fun setCurrentScale(currentScale: Int) {
        mCurrentScale = currentScale
        invalidate()
    }

    private fun drawPointer(canvas: Canvas) {
        canvas.save()
        canvas.translate(centerWidth, centerHeight)
        canvas.rotate(starAngle + mCurrentScale * sweepAngle / markSpots)
        canvas.drawLine(0f, 0f, pointerLength, 0f, paint)
        canvas.restore()
    }

    private fun drawMark(canvas: Canvas) {
        canvas.save()
        canvas.translate(centerWidth, centerHeight)
        paint.pathEffect = pathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null
        canvas.drawPath(path, paint)
        canvas.restore()
    }

}