package com.example.taskboard

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_choose.*

class choose : Activity() {
    private fun View.addClickScale(scale: Float = 0.9f, duration: Long = 150) {
        this.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    this.animate().scaleX(scale).scaleY(scale).setDuration(duration).start()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    this.animate().scaleX(1f).scaleY(1f).setDuration(duration).start()
                }
            }
            // 点击事件处理，交给View自身
            this.onTouchEvent(event)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)
        val userName=intent.getStringExtra("user_name")
        val userId=intent.getStringExtra("user_id")
        create.setOnClickListener {
            create.addClickScale()
            val intent = Intent(this, CreateActivity::class.java)
            intent.putExtra("user_name",userName)
            intent.putExtra("user_id",userId)
            startActivity(intent)
            overridePendingTransition(R.anim.scale_in,R.anim.scale_out)
        }
        receive.setOnClickListener {
            receive.addClickScale()
            val intent = Intent(this, ReceiveListActivity::class.java)
            intent.putExtra("user_id",userId)
            intent.putExtra("user_name",userName)
            startActivity(intent)
            overridePendingTransition(R.anim.right_tran_in,R.anim.right_tran_out)
        }
        received.setOnClickListener {
            received.addClickScale()
            val intent = Intent(this, ReceivedListActivity::class.java)
            intent.putExtra("user_id",userId)
            startActivity(intent)
            overridePendingTransition(R.anim.right_tran_in,R.anim.right_tran_out)
        }
        receiving.setOnClickListener {
            receiving.addClickScale()
            val intent = Intent(this, ReceivingListActivity::class.java)
            intent.putExtra("user_id",userId)
            startActivity(intent)
            overridePendingTransition(R.anim.right_tran_in,R.anim.right_tran_out)
        }
        created.setOnClickListener {
            created.addClickScale()
            val intent = Intent(this, CreatedListActivity::class.java)
            intent.putExtra("user_id",userId)
            startActivity(intent)
            overridePendingTransition(R.anim.right_tran_in,R.anim.right_tran_out)
        }
        creating.setOnClickListener {
            creating.addClickScale()
            val intent = Intent(this, CreatingListActivity::class.java)
            intent.putExtra("user_id",userId)
            startActivity(intent)
            overridePendingTransition(R.anim.right_tran_in,R.anim.right_tran_out)
        }
        indentity.setOnClickListener {
            val intent = Intent(this, Identity::class.java)
            intent.putExtra("user_id",userId)
            intent.putExtra("user_name",userName)
            startActivity(intent)
            overridePendingTransition(R.anim.left_tran_in,R.anim.left_tran_out)
            finish()
        }
        grade.setOnClickListener {
            val intent = Intent(this, MedalActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.left_tran_in,R.anim.left_tran_out)
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        AlertDialog.Builder(this).apply {
            setTitle("确认退出")
            setMessage("您确定要退出吗？")
            setPositiveButton("退出")
            {
                    _, _ ->
                    finish()
            }
            setNegativeButton("取消")
            {
                    _, _ ->
            }
            show()
        }
        return super.onKeyDown(keyCode, event)
    }
}
