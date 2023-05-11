package com.example.toyrobert

import android.util.Log

class LeftCommand:CommandExecution() {
    val TAG ="ClassName"
    init {
        Log.i(TAG,"LeftCommand")
    }
    override fun execute(robert: Robot) {
        if (!robert.isOnTable()){
            Log.i(TAG,"true")

        }else{
            when(robert.getCardinalDirection()){
                RobotDirection.NORTH->
                    robert.setCardinalDirection(RobotDirection.WEST)
                RobotDirection.SOUTH->
                    robert.setCardinalDirection(RobotDirection.EAST)
                RobotDirection.EAST->
                    robert.setCardinalDirection(RobotDirection.NORTH)
                RobotDirection.WEST->
                    robert.setCardinalDirection(RobotDirection.SOUTH)
            }
            Log.i(TAG,"Left 90"+robert.getCardinalDirection())
        }

    }
}