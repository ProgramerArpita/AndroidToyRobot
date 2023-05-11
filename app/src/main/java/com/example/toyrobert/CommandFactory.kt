package com.example.toyrobert

import android.util.Log

class CommandFactory() {

    private val PLACE_COMMAND_REGEX = Regex("^P")

    fun getCommand(commandString: String): CommandExecution? {
        if (PLACE_COMMAND_REGEX.containsMatchIn(commandString)) {
            return PlaceCommand(commandString)
        } else if (commandString.equals(CommandType.LEFT.name)) {
            return LeftCommand()
        } else if (commandString.equals(CommandType.RIGHT.name)) {
            return RightCommand()
        } else if (commandString.equals(CommandType.MOVE.name)) {
            return MoveCommand()
        } else if (commandString.equals(CommandType.REPORT.name) ) {
            return ReportCommand()
        }else{
            return null
        }

    }



}

enum class CommandType {
    PLACE,  MOVE, LEFT, RIGHT, REPORT

}
