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
import kotlinx.android.synthetic.main.activity_receive.*

class ReceiveActivity : Activity() {

    var userId:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive)
        userId = intent.getStringExtra("user_id")
        val userName =intent.getStringExtra("user_name")
        var misId = SpannableStringBuilder(intent.getStringExtra("mission_id")).toString()
        var bmobQuery: BmobQuery<MissionTable> = BmobQuery()
        bmobQuery.getObject(misId, object : QueryListener<MissionTable>() {
            override fun done(mission: MissionTable?, ex: BmobException?) {
                if (ex == null) {
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

        button.setOnClickListener {
            var mission = MissionTable()
            mission.objectId = misId
            mission.receiveB = "true"
            mission.receiverID = userId
            mission.receiverName=userName
            mission.update(object : UpdateListener() {
                override fun done(ex: BmobException?) {
                    if (ex == null) {
                        Toast.makeText(this@ReceiveActivity, "承接成功", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@ReceiveActivity, ex.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })

            //更新list的列表
            val intent = Intent(this, ReceiveListActivity::class.java)
            intent.putExtra("user_id",userId)
            startActivity(intent)
            overridePendingTransition(R.anim.overturn_in, R.anim.overturn_out)
            finish()
        }

        back.setOnClickListener {
            val intent = Intent(this, ReceiveListActivity::class.java)
            intent.putExtra("user_id",userId)
            startActivity(intent)
            overridePendingTransition(R.anim.overturn_in, R.anim.overturn_out)
            finish()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val intent = Intent(this, ReceiveListActivity::class.java)
        intent.putExtra("user_id",userId)
        startActivity(intent)
        overridePendingTransition(R.anim.overturn_in, R.anim.overturn_out)
        finish()
        return super.onKeyDown(keyCode, event)
    }
}