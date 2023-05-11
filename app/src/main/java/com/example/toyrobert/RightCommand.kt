package com.example.toyrobert

import android.util.Log

class RightCommand: CommandExecution() {
    val TAG ="ClassName"

    override fun execute(robert: Robert) {
        if (!robert.isOnTable()){
            Log.i(TAG,"true")

        }else{
            when(robert.getCardinalDirection()){
                RobertDirection.NORTH->
                    robert.setCardinalDirection(RobertDirection.EAST)
                RobertDirection.SOUTH->
                    robert.setCardinalDirection(RobertDirection.WEST)
                RobertDirection.EAST->
                    robert.setCardinalDirection(RobertDirection.SOUTH)
                RobertDirection.WEST->
                    robert.setCardinalDirection(RobertDirection.NORTH)
            }
            Log.i(TAG,"Left 90"+robert.getCardinalDirection())
        }


    }
}