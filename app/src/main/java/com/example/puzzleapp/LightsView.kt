package com.example.puzzleapp

import android.content.Context
import android.view.View

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import kotlin.math.min

class LightsView: View {
    private val TAG = "LightsView"

    private val bg = Color.BLACK
    private val bgGrid = Color.BLUE
    private val fgOff = Color.DKGRAY
    private val fgOn = Color.YELLOW

    private val cols = intArrayOf(fgOff, fgOn)

    private var n = 5
    var model: LightsModel? = null
    private var size = 0

    private var minLen = 0
    private var gridSquareLen = 0
    private var xOff = 0
    private var yOff = 0

    private var curX = 0
    private var curY = 0

    private val painter = Paint()
    private var rectFg = RectF()
    private var rectFtile = RectF()

    constructor(context: Context) : super(context) {
        setup(context, "Lights View Secondary Constructor 1")
    }

    constructor(context: Context, lightsModel: LightsModel) : super(context) {
        model = lightsModel
        setup(context, "Light View Secondary Constructor 2")
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        setup(context, "Lights View Secondary Constructor 3")
    }

    private fun checkModel() = if (model != null) {
        n = model!!.n
    } else {
        Log.i(TAG, "Creating Lights Model")
        n = 5
        model = LightsModel(n)
    }

    private fun setup(context: Context, cons: String) {
        Log.i(TAG, cons)

        checkModel()

        setOnClickListener {
            Log.i(TAG, "Clicked!")

            val i = ((curX - xOff.toFloat()) / size.toFloat()).toInt()
            val j = ((curY - yOff.toFloat()) / size.toFloat()).toInt()
            model?.tryFlip(i, j)
            model?.getScore()?.let { score -> (context as MainActivity).updateScore(score) }
            postInvalidate()
        }

        setOnTouchListener { v, m ->
            curX = m.x.toInt()
            curY = m.y.toInt()

            when (m.action) {
                MotionEvent.ACTION_UP -> {
                    v.performClick()
                }
            }

            true
        }
    }

    private fun setGeometry() {
        val midX = width / 2
        val midY = height / 2
        minLen = min(width, height)

        gridSquareLen = (minLen / n) * n

        size = gridSquareLen / n
        xOff = midX - gridSquareLen / 2
        yOff = midY - gridSquareLen / 2
    }

    private fun drawTile(canvas: Canvas, cx: Int, cy: Int, paint: Paint) {
        val length = (size * 7) / 8
        val rad = (size / 6).toFloat()

        val x = cx - length / 2
        val y = cy - length / 2

        rectFtile.set(x.toFloat(), y.toFloat(), (x + length).toFloat(), (y + length).toFloat())
        canvas.drawRoundRect(rectFtile, rad, rad, paint)
    }

    override fun onDraw(canvas: Canvas) {
        painter.isAntiAlias = true
        painter.style = Paint.Style.FILL
        painter.color = bg
        setGeometry()

        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), painter)

        painter.color = bgGrid
        rectFg.set(xOff.toFloat(), yOff.toFloat(), (xOff + gridSquareLen).toFloat(), (yOff + gridSquareLen).toFloat())
        canvas.drawRoundRect(rectFg, 5F, 5F, painter)

        for (i in 0 until n) {
            for (j in 0 until n) {
                val cx = xOff + size * i + size / 2
                val cy = yOff + size * j + size / 2
                if (model != null) {
                    painter.color = cols[model!!.grid[i][j]]
                }
                drawTile(canvas, cx, cy, painter)
            }
        }
    }
}