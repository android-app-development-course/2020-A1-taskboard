package com.example.taskboard

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_medal.*

class MedalActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medal)
        back3.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.right_tran_in,R.anim.right_tran_out)
        }
    }
}
