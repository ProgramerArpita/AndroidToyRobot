package com.example.toyrobert.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toyrobert.model.CommandType
import com.example.toyrobert.model.Robot
import com.example.toyrobert.model.RobotManager

class MainViewModel(private val robotManager: RobotManager) : ViewModel() {
    private var result = MutableLiveData<String>()
    val outPutResult: LiveData<String> get() = result

    private val inputData = ArrayList<String>()
    val inputListLiveData = MutableLiveData<List<String>>()


    fun getPlaceCommand(command: String, robot: Robot) {
        robotManager.placeCommandFunction(command, robot)
        addInputData(command)
    }

    fun getLeftDirection(robot: Robot) {
        robotManager.leftCommand(robot)
        addInputData(CommandType.LEFT.name)
    }

    fun getRightDirection(robot: Robot) {
        robotManager.rightCommand(robot)
        addInputData(CommandType.RIGHT.name)
    }

    fun move(robot: Robot) {
        robotManager.moveCommand(robot)
        addInputData(CommandType.MOVE.name)
    }

    fun report(robot: Robot) {
        addInputData(CommandType.REPORT.name)
        result.value = robot.getCurrentStatus()
    }

    private fun addInputData(command: String) {
        inputData.add(command)
        inputListLiveData.value = inputData
    }

    fun clearInputData() {
        inputData.clear()
        inputListLiveData.value = inputData
    }


}