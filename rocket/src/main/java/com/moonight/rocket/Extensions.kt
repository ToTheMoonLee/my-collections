package com.moonight.rocket

import android.content.res.Resources
import android.util.TypedValue

/**
 * @author lixiangyue
 * Date  : 2021/9/6 11:42 AM
 * Description:
 */
val Float.dp: Float // [xxhdpi](360 -> 1080)
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Int.dp: Int // [xxhdpi](360 -> 1080)
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()