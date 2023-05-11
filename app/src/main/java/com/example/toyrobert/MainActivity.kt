package com.example.toyrobert

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var tvXPosition:TextView
    lateinit var tvYposition:TextView
    lateinit var tvFace:TextView
    lateinit var tvoutPut:TextView
    lateinit var etOutput:EditText
    lateinit var etInput:EditText
    lateinit var btPlace:Button
    lateinit var btReport:Button
    lateinit var btLeft:Button
    lateinit var btRight:Button
    lateinit var btmove:Button
    lateinit var inputList:ArrayList<String>
    lateinit var inputXPosition:String
    lateinit var inputYposition:String
    lateinit var inputDirection:String
    lateinit var report: Report

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val positionList = resources.getStringArray(R.array.position)
        val faceList = resources.getStringArray(R.array.face)
        val spX = findViewById<Spinner>(R.id.spinner_x)
        val spY= findViewById<Spinner>(R.id.spinner_y)
        val spFace = findViewById<Spinner>(R.id.spinner_face)
         btPlace = findViewById<Button>(R.id.bt_Place)
         btReport = findViewById<Button>(R.id.bt_report)
         btLeft = findViewById<Button>(R.id.bt_Left)
         btRight = findViewById<Button>(R.id.bt_Right)
         btmove = findViewById<Button>(R.id.bt_Move)
         tvXPosition = findViewById<TextView>(R.id.tv_xPosition)
         tvYposition = findViewById<TextView>(R.id.tv_yPosition)
         tvoutPut = findViewById<TextView>(R.id.tv_output)
         etOutput = findViewById<EditText>(R.id.et_OutPutNumber)
         etInput = findViewById<EditText>(R.id.et_InputNumber)
         tvFace = findViewById<TextView>(R.id.tv_face)
         inputList = ArrayList()
         btPlace.setOnClickListener(this)
        btReport.setOnClickListener(this)
        btLeft.setOnClickListener(this)
        btRight.setOnClickListener(this)
        btmove.setOnClickListener(this)


        initialRobertPosition()

        if (spX != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, positionList)
            spX.adapter = adapter

            spX.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                    tvXPosition.text = "X"+positionList[position].toString()
                    inputXPosition = positionList[position].toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        if (spY != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, positionList)
            spY.adapter = adapter

            spY.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    tvYposition.text = "Y"+positionList[position].toString()
                    inputYposition = positionList[position].toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        if (spFace != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, faceList)
            spFace.adapter = adapter

            spFace.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    tvFace.text = faceList[position].toString()
                    inputDirection =faceList[position].toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        }

    private fun initialRobertPosition() {

        btLeft.isEnabled= false
        btRight.isEnabled= false
        btmove.isEnabled= false
        btReport.isEnabled= false
        tvoutPut.setTextColor(getResources().getColor(R.color.teal_200));
        tvoutPut.text = getString(R.string.initial_position)
        etOutput.setText(getString(R.string.Initial_Msg))
    }

    private fun outPutofRobert(tvMsg: String, etMsg: String) {
        tvoutPut.text = tvMsg
        etOutput.setText(etMsg)


    }
    private fun errorOutPutofRobert(tvMsg: String, etMsg: String) {
        tvoutPut.setTextColor(getResources().getColor(R.color.texterror));
        tvoutPut.text = tvMsg
        etOutput.setText(etMsg)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.bt_Place->{
               // Log.i("clicked","btPlace")
                if (btPlace.text.toString().equals("Clear")){
                    btPlace.setText("Place")
                    etInput.setText("")
                    etInput.setHint("Input")
                    initialRobertPosition()
                }else{
                    if (tvXPosition.text.trim().equals(getString(R.string.XPosition)) && tvYposition.text.trim().equals(getString(
                            R.string.Yposition)) && tvFace.text.trim().equals(getString(R.string.Direction))){
                        Toast.makeText(this,"Please select X,Y and Direction from DropDown",Toast.LENGTH_LONG).show()
                        Log.i(getString(R.string.ERROR),getString(R.string.place_error))
                        errorOutPutofRobert(getString(R.string.place_error),getString(R.string.initial_position))


                    }else{
                        activeButton()
                        btPlace.setBackgroundColor(getResources().getColor(R.color.teal_700))
                        btPlace.setTextColor(getResources().getColor(R.color.white))
                        inputList.add("Place $inputXPosition,$inputYposition,$inputDirection")
                        inputOfRobert()

                    }
                }



            }
            R.id.bt_Left->{
                btLeft.setBackgroundColor(getResources().getColor(R.color.teal_700))
                btLeft.setTextColor(getResources().getColor(R.color.white))
                inputList.add("LEFT")
                inputOfRobert()


            }
            R.id.bt_Right->{
                btRight.setBackgroundColor(getResources().getColor(R.color.teal_700))
                btRight.setTextColor(getResources().getColor(R.color.white))
                inputList.add("RIGHT")
                inputOfRobert()
            }
            R.id.bt_Move->{
                btmove.setBackgroundColor(getResources().getColor(R.color.teal_700))
                btmove.setTextColor(getResources().getColor(R.color.white))
                inputList.add("MOVE")
                inputOfRobert()

            }
            R.id.bt_report->{
                inputList.add(CommandType.REPORT.name)
                var robert = Robert()

                inputList.forEach {
                    Log.i("Print12",""+it.toString())

                    var commandExecution : CommandExecution? = CommandFactory().getCommand(it.toString())
                    commandExecution?.execute(robert)

                }
                report = Report(robert.getCurrentStatus().toString())
                println("FinalData11 ${report.finalStatus}")
                outPutofRobert(getString(R.string.robert_position),report.finalStatus)
                inputList.clear()
                inactiveButton()


            }
        }
    }

    private fun inactiveButton() {

        btLeft.isClickable= false
        btRight.isClickable= false
        btmove.isClickable= false
        btReport.isClickable= false


        btLeft.setBackgroundColor(getResources().getColor(R.color.grey))
        btRight.setBackgroundColor(getResources().getColor(R.color.grey))
        btmove.setBackgroundColor(getResources().getColor(R.color.grey))
        btReport.setBackgroundColor(getResources().getColor(R.color.grey))

        btPlace.setBackgroundColor(getResources().getColor(R.color.black))
        btPlace.setTextColor(getResources().getColor(R.color.white))

        btLeft.setTextColor(getResources().getColor(R.color.white))
        btRight.setTextColor(getResources().getColor(R.color.white))
        btmove.setTextColor(getResources().getColor(R.color.white))
        btReport.setTextColor(getResources().getColor(R.color.white))

        btPlace.setText("Clear")
        btPlace.isClickable = true
        btPlace.isEnabled = true


    }

    private fun inputOfRobert() {

        tvoutPut.setText("")
        etOutput.setText("")
        etInput.setText(inputList.toString())
        btPlace.isEnabled = false



    }

    private fun activeButton() {
        btLeft.isEnabled= true
        btRight.isEnabled= true
        btmove.isEnabled= true
        btReport.isEnabled= true
        btLeft.setBackgroundColor(getResources().getColor(R.color.black))
        btRight.setBackgroundColor(getResources().getColor(R.color.black))
        btmove.setBackgroundColor(getResources().getColor(R.color.black))
        btReport.setBackgroundColor(getResources().getColor(R.color.black))

        btLeft.setTextColor(getResources().getColor(R.color.white))
        btRight.setTextColor(getResources().getColor(R.color.white))
        btmove.setTextColor(getResources().getColor(R.color.white))
        btReport.setTextColor(getResources().getColor(R.color.white))

    }


}
