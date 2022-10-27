package com.example.puzzleapp

import org.junit.Test

class LightsModelTest {
    @Test
    fun main() {
        var model = LightsModel(5)

        println(model)

        model.tryFlip(2, 2)
        println(model)

        model.tryFlip(1, 2)
        println(model)

        model.tryFlip(1, 1)
        println(model)
    }
}