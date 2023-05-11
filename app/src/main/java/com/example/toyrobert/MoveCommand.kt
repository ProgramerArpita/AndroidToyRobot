package com.example.toyrobert

import android.util.Log

class MoveCommand:CommandExecution() {
    val TAG ="ClassName"
    override fun execute(robert: Robert) {
        if (!robert.isOnTable()){
            Log.i(TAG,"true")

        }else{
            when(robert.getCardinalDirection()){
                RobertDirection.NORTH-> {
                    if (robert.getYPosition()!! < robert.maxPosition) {
                        robert.increaseYPosition();
                        Log.i(TAG,"The robot is moving ")
                    } else {
                        Log.i(TAG,"Move command ignored")
                    }
                }
                RobertDirection.SOUTH-> {
                    if (robert.getYPosition()!! > robert.maxPosition) {
                        robert.decreaseYPosition();
                        Log.i(TAG,"The robot is moving ")
                    } else {
                        Log.i(TAG,"Move command ignored")
                    }
                }
                RobertDirection.EAST-> {
                    Log.i(TAG,"Position ${robert.getXPosition()}")
                    if (robert.getXPosition()!! < robert.maxPosition) {
                        robert.increaseXPosition()
                        Log.i(TAG,"Position ${robert.getXPosition()}")
                        Log.i(TAG,"The robot is moving EAST ")
                    } else {
                        Log.i(TAG,"Move command ignored")
                    }
                }
                RobertDirection.WEST-> {
                    if (robert.getXPosition()!! > robert.minPosittion) {
                        robert.decreaseXPosition()
                        Log.i(TAG,"The robot is moving ")
                    } else {
                        Log.i(TAG,"Move command ignored")
                    }
                }


            }
            Log.i(TAG,"Move Robert"+robert.getCurrentStatus())
        }
    }
}