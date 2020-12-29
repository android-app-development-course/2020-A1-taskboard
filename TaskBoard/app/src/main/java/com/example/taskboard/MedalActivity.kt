package com.example.taskboard

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        finish()
        overridePendingTransition(R.anim.right_tran_in,R.anim.right_tran_out)
        return super.onKeyDown(keyCode, event)
    }
}
