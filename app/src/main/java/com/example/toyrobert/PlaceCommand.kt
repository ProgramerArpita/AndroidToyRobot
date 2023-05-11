package com.example.toyrobert

import android.util.Log

class PlaceCommand(val commandString: String): CommandExecution() {
    init {
        Log.i("Execute","PlaceCommand")
    }

    override fun execute(robert: Robot) {
        Log.i("placementParms11",""+robert.getCurrentStatus())
        var placementParms = commandString.split(" ")
        var placementData = placementParms[1].toString()
        var data = placementData.split(",")
        var xAxisData = data[0].toInt()
        var yAxisData = data[1].toInt()
        var direction = data[2]


        if (xAxisData<=robert.maxPosition && xAxisData>=robert.minPosittion
            && yAxisData<=robert.maxPosition && yAxisData >=robert.minPosittion){
            robert.setXPosition(xAxisData)
            robert.setYPosition(yAxisData)
            robert.setCardinalDirection(RobotDirection.valueOf(direction))
            Log.i("Robert current Position",""+robert.getCurrentStatus())
        }else{
            Log.i("Robert current Position","Invalid Place command")
        }


    }
}