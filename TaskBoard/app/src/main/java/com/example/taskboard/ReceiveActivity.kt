package com.example.taskboard

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_receive.*

class ReceiveActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive)
        button.setOnClickListener {
            Toast.makeText(this,"承接成功", Toast.LENGTH_SHORT).show()
            finish()
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
        }
        back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
        }
    }
}
