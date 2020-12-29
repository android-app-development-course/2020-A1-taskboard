package com.example.taskboard

import android.app.Activity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.KeyEvent
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.UpdateListener
import kotlinx.android.synthetic.main.activity_received.*
import kotlinx.android.synthetic.main.activity_received.back
import kotlinx.android.synthetic.main.activity_received.mission_name
import kotlinx.android.synthetic.main.activity_received.xiangqing

class ReceivedActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_received)
        var misId= SpannableStringBuilder(intent.getStringExtra("mission_id")).toString()
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
                        if(mission.receiveT=="true")
                        {
                            accept.isEnabled=false
                        }
                    }
                }
            }
        })

        accept.setOnClickListener {
            var mission=MissionTable()
            mission.objectId=misId
            mission.receiveT="true"
            mission.update(object : UpdateListener(){
                override fun done(ex: BmobException?) {
                    if (ex == null) {
                        Toast.makeText(this@ReceivedActivity,"奖励已领取", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@ReceivedActivity, ex.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
            accept.isEnabled=false
            accept.setText("已领取")
            finish()
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
        }
        back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        finish()
        overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
        return super.onKeyDown(keyCode, event)
    }
}