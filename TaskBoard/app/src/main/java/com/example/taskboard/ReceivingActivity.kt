package com.example.taskboard

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_receiving.*

class ReceivingActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiving)
        delete.setOnClickListener {
            Toast.makeText(this,"已放弃任务", Toast.LENGTH_SHORT).show()
            finish()
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
        }
        button.setOnClickListener {
            Toast.makeText(this,"任务已完成", Toast.LENGTH_SHORT).show()
            finish()
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
        }
        back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
        }
    }
}
