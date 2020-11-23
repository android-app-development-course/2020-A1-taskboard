package com.example.taskboard

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        button.setOnClickListener {
            Toast.makeText(this,"发布成功", Toast.LENGTH_SHORT).show()
            finish()
            overridePendingTransition(R.anim.scale2_in,R.anim.scale2_out)
        }
        back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.scale2_in,R.anim.scale2_out)
        }
    }
}
