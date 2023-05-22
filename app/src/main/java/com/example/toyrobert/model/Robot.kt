package com.example.toyrobert.model


class Robot {
    val maxPosition: Int = 4
    val minPosition = 0
    private var xPosition: Int = 0
    private var yPosition: Int = 0
    private var robertDirection: RobotDirection? = null

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
        return robertDirection != null && xPosition <= maxPosition && xPosition >= minPosition && yPosition <= maxPosition && yPosition >= minPosition
    }

    fun getCurrentStatus(): String {
        return "( " + xPosition.toString() + " ," + yPosition.toString() + " ," + robertDirection.toString() + " )"
    }

    fun increaseYPosition() {
        yPosition++
    }

    fun decreaseYPosition() {
        yPosition--
    }

    fun increaseXPosition() {
        xPosition++
    }

    fun decreaseXPosition() {
        xPosition--
    }
}