package com.example.toyrobert.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toyrobert.model.CommandType
import com.example.toyrobert.model.Robot
import com.example.toyrobert.model.RobotManager

class MainViewModel(private val robotManager: RobotManager) :ViewModel() {
    private var result = MutableLiveData<String>()
    private var inputList = MutableLiveData<ArrayList<String>>()
    val outPutResult:LiveData<String> get() = result
    val finalInputList:LiveData<ArrayList<String>> get() = inputList

    private val mIssuePosts = ArrayList<String>()
     val mIssuePostLiveData = MutableLiveData<List<String>>()


    fun getPlaceCommand(command: String, robot: Robot){
        robotManager.placeCommandFunction(command,robot)
        inputList.value?.add(command)
       // inputList.run { value?.add(command) ?: "Default" }
        addIssuePost(command)
       // result.value = robot.getCurrentStatus()

    }
    fun getLeftDirection(robot: Robot){
        robotManager.leftCommand(robot)
        addIssuePost(CommandType.LEFT.name)
       // result.value = robot.getCurrentStatus()
    }
    fun getRightDirection(robot: Robot){
        robotManager.rightCommand(robot)
        addIssuePost(CommandType.RIGHT.name)
       // result.value = robot.getCurrentStatus()
    }
    fun move(robot: Robot){
        robotManager.moveCommand(robot)
        addIssuePost(CommandType.MOVE.name)
    }
    fun report(robot: Robot){
        addIssuePost(CommandType.REPORT.name)
        result.value = robot.getCurrentStatus()
    }
    private fun addIssuePost(command: String) {
        mIssuePosts.add(command)
        mIssuePostLiveData.value = mIssuePosts
    }


}