package com.example.toyrobert.view

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.toyrobert.*
import com.example.toyrobert.databinding.ActivityMainBinding
import com.example.toyrobert.model.Robot
import com.example.toyrobert.model.RobotManager
import com.example.toyrobert.viewmodel.MainViewModel
import com.example.toyrobert.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var robot: Robot
    lateinit var inputXPosition: String
    lateinit var inputYPosition: String
    lateinit var inputDirection: String

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModelFactory = MainViewModelFactory(RobotManager())
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        robot = Robot()
        initialUI()
        mainBinding.btPlace.setOnClickListener(this)
        mainBinding.btLeft.setOnClickListener(this)
        mainBinding.btRight.setOnClickListener(this)
        mainBinding.btMove.setOnClickListener(this)
        mainBinding.btReport.setOnClickListener(this)
        viewModel.outputResult.observe(this) {
            mainBinding.etOutPutNumber.setText(it)
        }
        viewModel.inputListLiveData.observe(this) {
            mainBinding.etInputNumber.setText(it.toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initialUI() {
        val positionList = resources.getStringArray(R.array.position)
        val faceList = resources.getStringArray(R.array.face)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, positionList)
        val adapterDirection = ArrayAdapter(this, android.R.layout.simple_spinner_item, faceList)
        mainBinding.apply {
            spinnerY.adapter = adapter
            spinnerX.adapter = adapter
            spinnerFace.adapter = adapterDirection
            tvOutput.text = resources.getText(R.string.initial_position)
            tvOutput.setTextColor(getColor(R.color.teal_200))
            etOutPutNumber.setText(R.string.initial_Msg)
        }
        mainBinding.spinnerX.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                (getString(R.string.x) + positionList[position].toString()).also {
                    mainBinding.tvXPosition.text = it
                }
                inputXPosition = positionList[position].toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        mainBinding.spinnerY.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                (getString(R.string.y) + positionList[position].toString()).also {
                    mainBinding.tvYPosition.text = it
                }
                inputYPosition = positionList[position].toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        mainBinding.spinnerFace.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                mainBinding.tvFace.text = faceList[position].toString()
                inputDirection = faceList[position].toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_Place -> {
                if (mainBinding.btPlace.text.toString() == getString(R.string.clear)) {
                    mainBinding.apply {
                        btPlace.text = getString(R.string.Place)
                        etInputNumber.setText("")
                        etInputNumber.hint = getString(R.string.input)
                        resetRobotPosition()
                    }
                    viewModel.clearInputData()

                } else {
                    if (mainBinding.tvXPosition.text.trim() == getString(R.string.xPosition) && mainBinding.tvYPosition.text.trim() == getString(
                            R.string.yPosition
                        ) && mainBinding.tvFace.text.trim() == getString(R.string.direction)
                    ) {
                        errorMsg(
                            getString(R.string.place_error),
                            getString(R.string.initial_position)
                        )
                    } else {
                        val inputData =
                            "Place ${inputXPosition},${inputYPosition},${inputDirection}"
                        mainBinding.apply {
                            tvOutput.text = getString(R.string.robert_position)
                            tvOutput.setTextColor(getColor(R.color.teal_200))
                        }
                        viewModel.getPlaceCommand(inputData, robot)
                        activeButton(getString(R.string.active))
                    }
                }


            }
            R.id.bt_Left -> {
                viewModel.getLeftDirection(robot)
            }
            R.id.bt_Right -> {
                viewModel.getRightDirection(robot)
            }
            R.id.bt_Move -> {
                viewModel.move(robot)
            }
            R.id.bt_report -> {
                viewModel.report(robot)
                activeButton(getString(R.string.reset))

            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun resetRobotPosition() {
        mainBinding.apply {
            tvXPosition.text = getString(R.string.xPosition)
            tvYPosition.text = getString(R.string.yPosition)
            tvFace.text = getString(R.string.direction)
            tvOutput.setTextColor(getColor(R.color.teal_200))
            tvOutput.text = getString(R.string.initial_position)
            etOutPutNumber.setText(getString(R.string.initial_Msg))
        }


    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun activeButton(state: String) {
        if (state == getString(R.string.active)) {
            mainBinding.apply {
                btLeft.isEnabled = true
                btRight.isEnabled = true
                btMove.isEnabled = true
                btReport.isEnabled = true

                btLeft.setBackgroundColor(getColor(R.color.black))
                btRight.setBackgroundColor(getColor(R.color.black))
                btMove.setBackgroundColor(getColor(R.color.black))
                btReport.setBackgroundColor(getColor(R.color.black))
                btLeft.setTextColor(getColor(R.color.white))
                btRight.setTextColor(getColor(R.color.white))
                btMove.setTextColor(getColor(R.color.white))
                btReport.setTextColor(getColor(R.color.white))
            }
        } else {
            mainBinding.apply {
                btPlace.text = getString(R.string.clear)
                btLeft.isEnabled = false
                btRight.isEnabled = false
                btMove.isEnabled = false
                btReport.isEnabled = false

                btLeft.setBackgroundColor(getColor(R.color.grey))
                btRight.setBackgroundColor(getColor(R.color.grey))
                btMove.setBackgroundColor(getColor(R.color.grey))
                btReport.setBackgroundColor(getColor(R.color.grey))

                btPlace.setBackgroundColor(getColor(R.color.black))
                btPlace.setTextColor(getColor(R.color.white))

                btLeft.setTextColor(getColor(R.color.white))
                btRight.setTextColor(getColor(R.color.white))
                btMove.setTextColor(getColor(R.color.white))
                btReport.setTextColor(getColor(R.color.white))
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun errorMsg(tvMsg: String, etMsg: String) {
        mainBinding.apply {
            tvOutput.setTextColor(getColor(R.color.texterror))
            tvOutput.text = tvMsg
            etOutPutNumber.setText(etMsg)
        }


    }
}










