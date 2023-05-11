package com.example.toyrobert

import android.util.Log

class LeftCommand:CommandExecution() {
    val TAG ="ClassName"
    init {
        Log.i(TAG,"LeftCommand")
    }
    override fun execute(robert: Robert) {
        if (!robert.isOnTable()){
            Log.i(TAG,"true")

        }else{
            when(robert.getCardinalDirection()){
                RobertDirection.NORTH->
                    robert.setCardinalDirection(RobertDirection.WEST)
                RobertDirection.SOUTH->
                    robert.setCardinalDirection(RobertDirection.EAST)
                RobertDirection.EAST->
                    robert.setCardinalDirection(RobertDirection.NORTH)
                RobertDirection.WEST->
                    robert.setCardinalDirection(RobertDirection.SOUTH)
            }
            Log.i(TAG,"Left 90"+robert.getCardinalDirection())
        }

    }
}