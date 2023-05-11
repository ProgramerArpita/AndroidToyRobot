package com.example.toyrobert

import android.util.Log

class MoveCommand:CommandExecution() {
    val TAG ="ClassName"
    val MOVE_IGNORE = "Move command ignored"
    override fun execute(robert: Robot) {
        if (!robert.isOnTable()){
            Log.i(TAG,"true")

        }else{
            when(robert.getCardinalDirection()){
                RobotDirection.NORTH-> {
                    if (robert.getYPosition()!! < robert.maxPosition) {
                        robert.increaseYPosition();
                        Log.i(TAG,"The robot is moving ")
                    } else {
                        robert.setTableFallCondition(MOVE_IGNORE)
                        Log.i(TAG,"Move command ignored")
                    }
                }
                RobotDirection.SOUTH-> {
                    if (robert.getYPosition()!! > robert.minPosittion) {
                        robert.decreaseYPosition();
                        Log.i(TAG,"The robot is moving ")
                    } else {
                        robert.setTableFallCondition(MOVE_IGNORE)
                        Log.i(TAG,"Move command ignored")
                    }
                }
                RobotDirection.EAST-> {
                    Log.i(TAG,"Position ${robert.getXPosition()}")
                    if (robert.getXPosition()!! < robert.maxPosition) {
                        robert.increaseXPosition()
                        Log.i(TAG,"Position ${robert.getXPosition()}")
                        Log.i(TAG,"The robot is moving EAST ")
                    } else {
                        robert.setTableFallCondition(MOVE_IGNORE)
                        Log.i(TAG,"Move command ignored")
                    }
                }
                RobotDirection.WEST-> {
                    if (robert.getXPosition()!! > robert.minPosittion) {
                        robert.decreaseXPosition()
                        Log.i(TAG,"The robot is moving ")
                    } else {
                        robert.setTableFallCondition(MOVE_IGNORE)
                        Log.i(TAG,"Move command ignored")
                    }
                }


            }
            Log.i(TAG,"Move Robert"+robert.getCurrentStatus())
        }
    }
}