package com.example.toyrobert.model

import android.util.Log

class RobotManager {
    private val TAG = "RobotManager"
    private val MOVEIGNORE = "Move command ignored"

    fun placeCommandFunction(commandString: String, robot: Robot) {
        val placementParams = commandString.split(" ")
        val placementData = placementParams[1]
        val data = placementData.split(",")
        val xAxisData = data[0].toInt()
        val yAxisData = data[1].toInt()
        val direction = data[2]


        if (xAxisData <= robot.maxPosition && xAxisData >= robot.minPosittion
            && yAxisData <= robot.maxPosition && yAxisData >= robot.minPosittion
        ) {
            robot.setXPosition(xAxisData)
            robot.setYPosition(yAxisData)
            robot.setCardinalDirection(RobotDirection.valueOf(direction))
            Log.i("Robot current position", "" + robot.getCurrentStatus())
        } else {
            Log.i("Robot current Position", "Invalid Place command")
        }
    }

    fun leftCommand(robot: Robot) {
        if (robot.isOnTable()) {
            when (robot.getCardinalDirection()) {
                RobotDirection.NORTH ->
                    robot.setCardinalDirection(RobotDirection.WEST)
                RobotDirection.SOUTH ->
                    robot.setCardinalDirection(RobotDirection.EAST)
                RobotDirection.EAST ->
                    robot.setCardinalDirection(RobotDirection.NORTH)
                RobotDirection.WEST ->
                    robot.setCardinalDirection(RobotDirection.SOUTH)
                else -> {}
            }
            Log.i(TAG, "Left 90" + robot.getCardinalDirection())
        }
    }

    fun rightCommand(robot: Robot) {
        if (robot.isOnTable()) {
            when (robot.getCardinalDirection()) {
                RobotDirection.NORTH ->
                    robot.setCardinalDirection(RobotDirection.EAST)
                RobotDirection.SOUTH ->
                    robot.setCardinalDirection(RobotDirection.WEST)
                RobotDirection.EAST ->
                    robot.setCardinalDirection(RobotDirection.SOUTH)
                RobotDirection.WEST ->
                    robot.setCardinalDirection(RobotDirection.NORTH)
                else -> {}
            }
            Log.i(TAG, "Left 90" + robot.getCardinalDirection())
        }
    }

    fun moveCommand(robot: Robot) {
        if (!robot.isOnTable()) {
            Log.i(TAG, "true")

        } else {
            when (robot.getCardinalDirection()) {
                RobotDirection.NORTH -> {
                    if (robot.getYPosition() < robot.maxPosition) {
                        robot.increaseYPosition()
                    } else {
                        robot.setTableFallCondition(MOVEIGNORE)
                    }
                }
                RobotDirection.SOUTH -> {
                    if (robot.getYPosition() > robot.minPosittion) {
                        robot.decreaseYPosition()
                    } else {
                        robot.setTableFallCondition(MOVEIGNORE)
                        Log.i(TAG, "Move command ignored")
                    }
                }
                RobotDirection.EAST -> {
                    if (robot.getXPosition() < robot.maxPosition) {
                        robot.increaseXPosition()
                        Log.i(TAG, "The robot is moving EAST ")
                    } else {
                        robot.setTableFallCondition(MOVEIGNORE)
                        Log.i(TAG, "Move command ignored")
                    }
                }
                RobotDirection.WEST -> {
                    if (robot.getXPosition() > robot.minPosittion) {
                        robot.decreaseXPosition()
                        Log.i(TAG, "The robot is moving ")
                    } else {
                        robot.setTableFallCondition(MOVEIGNORE)
                        Log.i(TAG, "Move command ignored")
                    }
                }


                else -> {}
            }
            Log.i(TAG, "Move Robert" + robot.getCurrentStatus())
        }
    }

}