package com.example.taskboard

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_identity.*

class Identity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identity)
        bt6.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.right_tran_in,R.anim.right_tran_out)
        }
        back2.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.right_tran_in,R.anim.right_tran_out)
        }
    }
}
