package com.example.taskboard

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_received.*

class ReceivedActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_received)
        accept.setOnClickListener {
            Toast.makeText(this,"奖励已领取", Toast.LENGTH_SHORT).show()
            accept.isEnabled=false
            accept.setText("已领取")
        }
        back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
        }
    }
}
