package com.example.taskboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import kotlinx.android.synthetic.main.activity_identity.*

class Identity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identity)
        mail.text = intent.getStringExtra("user_id")
        name.text=intent.getStringExtra("user_name")

        bt6.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.right_tran_in,R.anim.right_tran_out)
        }

        back2.setOnClickListener {
            val intent = Intent(this, choose::class.java)
            intent.putExtra("user_id",mail.text.toString())
            intent.putExtra("user_name",name.text.toString())
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.right_tran_in,R.anim.right_tran_out)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val intent = Intent(this, choose::class.java)
        intent.putExtra("user_id",mail.text.toString())
        intent.putExtra("user_name",name.text.toString())
        startActivity(intent)
        finish()
        overridePendingTransition(R.anim.right_tran_in,R.anim.right_tran_out)
        return super.onKeyDown(keyCode, event)
    }
}
