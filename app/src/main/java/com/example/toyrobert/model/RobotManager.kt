package com.example.toyrobert.model

import android.util.Log

private const val TAG = "RobotManager"
private const val INVALID_COMMAND = "Invalid Place command"
private const val INVALID_DIRECTION = "Not a valid Direction"
private const val MOVE_IGNORE = "Move command ignored"

class RobotManager {
    fun placeCommandFunction(commandString: String, robot: Robot) {
        val placementParams = commandString.split(" ")
        val placementData = placementParams[1]
        val data = placementData.split(",")
        val xAxisData = data[0].toInt()
        val yAxisData = data[1].toInt()
        val direction = data[2]

        if (xAxisData <= robot.maxPosition && xAxisData >= robot.minPosition
            && yAxisData <= robot.maxPosition && yAxisData >= robot.minPosition
        ) {
            robot.setXPosition(xAxisData) // set x position of Robot
            robot.setYPosition(yAxisData) // set y position of Robot
            robot.setCardinalDirection(RobotDirection.valueOf(direction)) //set direction of Robot
            Log.i(TAG, "" + robot.getCurrentStatus())
        } else {
            Log.i(TAG, INVALID_COMMAND)
        }
    }

    //  Rotate the robot 90 degrees in left direction
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
                else -> {
                    Log.i(TAG, INVALID_DIRECTION)
                }
            }
            Log.i(TAG, "Left 90" + robot.getCardinalDirection())
        }
    }

    // Rotate the robot 90 degree in right direction
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
                else -> {
                    Log.i(TAG, INVALID_DIRECTION)
                }
            }
            Log.i(TAG, "Right 90" + robot.getCardinalDirection())
        }
    }

    // Move Robot one unit forward in the direction it is facing
    fun moveCommand(robot: Robot) {
        if (robot.isOnTable()) {
            when (robot.getCardinalDirection()) {
                RobotDirection.NORTH -> {
                    if (robot.getYPosition() < robot.maxPosition) {
                        robot.increaseYPosition()
                    } else {
                        Log.i(TAG, MOVE_IGNORE)
                    }
                }
                RobotDirection.SOUTH -> {
                    if (robot.getYPosition() > robot.minPosition) {
                        robot.decreaseYPosition()
                    } else {
                        Log.i(TAG, MOVE_IGNORE)
                    }
                }
                RobotDirection.EAST -> {
                    if (robot.getXPosition() < robot.maxPosition) {
                        robot.increaseXPosition()
                    } else {
                        Log.i(TAG, MOVE_IGNORE)
                    }
                }
                RobotDirection.WEST -> {
                    if (robot.getXPosition() > robot.minPosition) {
                        robot.decreaseXPosition()
                    } else {
                        Log.i(TAG, MOVE_IGNORE)
                    }
                }
                else -> {
                    Log.i(TAG, INVALID_DIRECTION)
                }
            }
            Log.i(TAG, "Move Robot" + robot.getCurrentStatus())
        }
    }

}