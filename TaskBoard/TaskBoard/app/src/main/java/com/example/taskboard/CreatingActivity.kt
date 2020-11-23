package com.example.taskboard

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_creating.*

class CreatingActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creating)
        cancel.setOnClickListener {
            Toast.makeText(this,"已取消发布", Toast.LENGTH_SHORT).show()
            cancel.isEnabled=false
            cancel.setText("已取消")
        }
        finish.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
        }
        back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
        }
    }
}
