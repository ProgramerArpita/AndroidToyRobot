package com.example.toyrobert

import android.util.Log

class ReportCommand:CommandExecution() {
    override fun execute(robert: Robot) {
        robert.getCurrentStatus()
        robert.getTableFallCondition()
        Log.i("ReportCommand",""+robert.getCurrentStatus())


    }
}