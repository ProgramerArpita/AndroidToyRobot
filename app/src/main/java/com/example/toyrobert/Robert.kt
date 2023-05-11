package com.example.toyrobert

import android.util.Log

class Robert(){
    val maxPosition:Int = 4
    val minPosittion = 0
     private var xPosition:Int=0
    private var yPosition:Int =0
    private var robertDirection:RobertDirection?=null

    fun getXPosition(): Int? {
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

    fun getCardinalDirection(): RobertDirection? {
        return robertDirection
    }

    fun setCardinalDirection(robertDirection: RobertDirection) {
        this.robertDirection = robertDirection
    }

    fun isOnTable(): Boolean {
        return xPosition != null && yPosition != null && robertDirection != null &&
                !(xPosition!! > maxPosition) && !(xPosition!! <minPosittion)&& !(yPosition!! >maxPosition) && !(yPosition!! <minPosittion)
    }

    fun getCurrentStatus(): String? {
        return xPosition.toString()+ yPosition.toString()+ robertDirection.toString()
    }


    fun increaseYPosition() {
        yPosition++
    }

    fun decreaseYPosition() {
       yPosition--
    }

    fun increaseXPosition() {

       xPosition++
        Log.i("Plus",""+xPosition)
    }

    fun decreaseXPosition() {
        xPosition--
    }


}