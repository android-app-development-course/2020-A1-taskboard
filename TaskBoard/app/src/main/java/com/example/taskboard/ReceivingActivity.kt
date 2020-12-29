package com.example.taskboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.KeyEvent
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.UpdateListener
import kotlinx.android.synthetic.main.activity_receiving.*
import kotlinx.android.synthetic.main.activity_receiving.back
import kotlinx.android.synthetic.main.activity_receiving.button
import kotlinx.android.synthetic.main.activity_receiving.mission_name
import kotlinx.android.synthetic.main.activity_receiving.textView12
import kotlinx.android.synthetic.main.activity_receiving.textView13
import kotlinx.android.synthetic.main.activity_receiving.textView14
import kotlinx.android.synthetic.main.activity_receiving.textView15
import kotlinx.android.synthetic.main.activity_receiving.xiangqing


class ReceivingActivity : Activity() {
    var userId:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiving)
        var misId= intent.getStringExtra("mission_id")
        userId=intent.getStringExtra("user_id")
        var  bmobQuery: BmobQuery<MissionTable> = BmobQuery()
        bmobQuery.getObject(misId,object : QueryListener<MissionTable>() {
            override fun done(mission:MissionTable?,ex: BmobException?){
                if (ex ==null){
                    if (mission != null) {
                        mission_name.text = mission.title.toString()
                        textView15.text=mission.publisherName.toString()
                        xiangqing.text=SpannableStringBuilder(mission.detail.toString())
                        textView14.text=SpannableStringBuilder(mission.reward.toString())
                        textView12.text=SpannableStringBuilder(mission.createdAt.toString())
                        textView13.text=SpannableStringBuilder(mission.ddlT.toString())
                    }
                }
            }
        })

        delete.setOnClickListener {
            var mission=MissionTable()
            mission.objectId=misId
            mission.receiveB="false"
            mission.receiverID=null
            mission.update(object : UpdateListener(){
                override fun done(ex: BmobException?) {
                    if (ex == null) {
                        Toast.makeText(this@ReceivingActivity, "已放弃任务", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@ReceivingActivity, ex.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
            val intent = Intent(this, ReceivingListActivity::class.java)
            intent.putExtra("user_id",userId)
            startActivity(intent)
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
            finish()
        }

        button.setOnClickListener {
            var mission=MissionTable()
            mission.objectId=misId
            mission.completeR="true"
            mission.update(object : UpdateListener(){
                override fun done(ex: BmobException?) {
                    if (ex == null) {
                        Toast.makeText(this@ReceivingActivity, "任务已完成,请等待发布人确认", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@ReceivingActivity, ex.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
            val intent = Intent(this, ReceivingListActivity::class.java)
            intent.putExtra("user_id",userId)
            startActivity(intent)
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
            finish()
        }

        back.setOnClickListener {
            val intent = Intent(this, ReceivingListActivity::class.java)
            intent.putExtra("user_id",userId)
            startActivity(intent)
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
            finish()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val intent = Intent(this, ReceivingListActivity::class.java)
        intent.putExtra("user_id",userId)
        startActivity(intent)
        overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
        finish()
        return super.onKeyDown(keyCode, event)
    }
}
