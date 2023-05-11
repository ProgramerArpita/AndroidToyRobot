package com.example.toyrobert

import android.util.Log

class RightCommand: CommandExecution() {
    val TAG ="ClassName"

    override fun execute(robert: Robot) {
        if (!robert.isOnTable()){
            Log.i(TAG,"true")

        }else{
            when(robert.getCardinalDirection()){
                RobotDirection.NORTH->
                    robert.setCardinalDirection(RobotDirection.EAST)
                RobotDirection.SOUTH->
                    robert.setCardinalDirection(RobotDirection.WEST)
                RobotDirection.EAST->
                    robert.setCardinalDirection(RobotDirection.SOUTH)
                RobotDirection.WEST->
                    robert.setCardinalDirection(RobotDirection.NORTH)
            }
            Log.i(TAG,"Left 90"+robert.getCardinalDirection())
        }


    }
}