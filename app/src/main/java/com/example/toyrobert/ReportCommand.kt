package com.example.toyrobert

import android.util.Log

class ReportCommand:CommandExecution() {
    override fun execute(robert: Robert) {
        robert.getCurrentStatus()
        Log.i("ReportCommand",""+robert.getCurrentStatus())


    }
}