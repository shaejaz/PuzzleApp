package com.example.puzzleapp

import java.lang.Exception

class LightsModel (gridSize: Int) {

    var n = gridSize

    var grid = Array(gridSize) { IntArray(gridSize) }

    var strict = false

    fun isSwitchedOn(x: Int, y: Int): Boolean {
        return grid[x][y] == 1
    }

    fun flipSwitch(x: Int, y: Int): Unit {
        val v = grid[x][y]
        grid[x][y] = if (v == 0) 1 else 0
    }

    fun flipLinesHorizontally(y: Int): Unit {
        for (i in 0 until n) {
            flipSwitch(i, y)
        }
    }

    fun flipLinesVertically(x: Int): Unit {
        for (i in 0 until n) {
            flipSwitch(x, i)
        }
    }

    fun flipLines(x: Int, y: Int): Unit {
        flipLinesHorizontally(y)
        flipLinesVertically(x)
        flipSwitch(x, y) // to revert the double flip
    }

    fun tryFlip(x: Int, y: Int): Unit {
        try {
            if (isSwitchedOn(x, y) || !strict) {
                flipLines(x, y)
            }
        } catch (e: Exception) {
        }
    }

    fun isSolved(): Boolean {
        return false
    }

    fun reset(): Unit {
        return
    }

    override fun toString(): String {
        val b = StringBuilder()
        for (i in 0 until n) {
            b.append(grid[i].contentToString() + "\n")
        }

        return b.toString()
    }
}