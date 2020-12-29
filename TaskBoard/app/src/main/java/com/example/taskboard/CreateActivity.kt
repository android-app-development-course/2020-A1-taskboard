package com.example.taskboard

import android.app.Activity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        val userName=intent.getStringExtra("user_name")
        val userId=intent.getStringExtra("user_id")
        textView15.text=SpannableStringBuilder(userName.toString())
        var missionTable=MissionTable()
        button.setOnClickListener {
            if(mission_name.text.toString()=="")
            {
                Toast.makeText(this@CreateActivity, "请填写任务名", Toast.LENGTH_SHORT).show()
            }
            else {
                missionTable.title = mission_name.text.toString()
                missionTable.publisherID = userId
                missionTable.reward = textView14.text.toString()
                missionTable.detail = xiangqing.text.toString()
                missionTable.ddlT = textView13.text.toString()
                missionTable.receiveB = "false"
                missionTable.completeR = "false"
                missionTable.publisherName = userName
                missionTable.completeC = "false"
                missionTable.save(object : SaveListener<String>() {
                    override fun done(objectId: String?, ex: BmobException?) {
                        if (ex == null) {
                            Toast.makeText(
                                this@CreateActivity,
                                "新增数据成功：$objectId",
                                Toast.LENGTH_SHORT
                            ).show()
                            button.isEnabled = false
                        } else {
                            Log.e("CREATE", "新增数据失败：" + ex.message)
                        }
                    }
                })
                finish()
                overridePendingTransition(R.anim.scale2_in, R.anim.scale2_out)
            }
        }
        back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.scale2_in,R.anim.scale2_out)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        finish()
        overridePendingTransition(R.anim.scale2_in,R.anim.scale2_out)
        return super.onKeyDown(keyCode, event)
    }
}
