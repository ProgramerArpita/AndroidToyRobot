package com.example.toyrobert.model

import android.util.Log

class Robot {
    val maxPosition: Int = 4
    val minPosittion = 0
    private var xPosition: Int = 0
    private var yPosition: Int = 0
    private var robertDirection: RobotDirection? = null
    private var errorMsg = ""

    fun getXPosition(): Int {
        return xPosition
    }

    fun setXPosition(xPosition: Int) {
        this.xPosition = xPosition
    }

    fun getYPosition(): Int {
        return yPosition
    }

    fun setYPosition(yPosition: Int) {
        this.yPosition = yPosition
    }

    fun getCardinalDirection(): RobotDirection? {
        return robertDirection
    }

    fun setCardinalDirection(robertDirection: RobotDirection) {
        this.robertDirection = robertDirection
    }

    fun isOnTable(): Boolean {
        return robertDirection != null && xPosition <= maxPosition && xPosition >= minPosittion && yPosition <= maxPosition && yPosition >= minPosittion
    }

    fun getCurrentStatus(): String {
        return "( " + xPosition.toString() + " ," + yPosition.toString() + " ," + robertDirection.toString() + " )"
    }

    fun setTableFallCondition(error: String) {
        this.errorMsg = error

    }

    fun getTableFallCondition(): String {
        return errorMsg

    }


    fun increaseYPosition() {
        yPosition++
    }

    fun decreaseYPosition() {
        yPosition--
    }

    fun increaseXPosition() {

        xPosition++
        Log.i("Plus", "" + xPosition)
    }

    fun decreaseXPosition() {
        xPosition--
    }


}