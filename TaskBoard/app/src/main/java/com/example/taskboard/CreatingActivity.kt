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
import kotlinx.android.synthetic.main.activity_creating.*
import kotlinx.android.synthetic.main.activity_creating.back
import kotlinx.android.synthetic.main.activity_creating.mission_name
import kotlinx.android.synthetic.main.activity_creating.textView12
import kotlinx.android.synthetic.main.activity_creating.textView13
import kotlinx.android.synthetic.main.activity_creating.textView14
import kotlinx.android.synthetic.main.activity_creating.textView15
import kotlinx.android.synthetic.main.activity_creating.xiangqing


class CreatingActivity : Activity() {
    var userId1:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creating)
        val userId=intent.getStringExtra("user_id")
        userId1=userId
        var misId= SpannableStringBuilder(intent.getStringExtra("mission_id")).toString()
        var  bmobQuery: BmobQuery<MissionTable> = BmobQuery()
        bmobQuery.getObject(misId,object : QueryListener<MissionTable>() {
            override fun done(mission:MissionTable?,ex: BmobException?){
                if (ex ==null){
                    if (mission != null) {
                        mission_name.text = mission.title.toString()
                        textView15.text=mission.receiverName.toString()
                        xiangqing.text=SpannableStringBuilder(mission.detail.toString())
                        textView14.text=SpannableStringBuilder(mission.reward.toString())
                        textView12.text=SpannableStringBuilder(mission.createdAt.toString())
                        textView13.text=SpannableStringBuilder(mission.ddlT.toString())
                        if(mission.completeR.toString()=="false")
                        {
                            finish.isEnabled=false
                        }
                        if(mission.receiveB.toString()=="true")
                        {
                            cancel.isEnabled=false
                        }
                    }
                }
            }
        })

        cancel.setOnClickListener {
            var mission=MissionTable()
            mission.objectId=misId
            mission.delete(object : UpdateListener() {
                override fun done(ex: BmobException?) {
                    if (ex == null) {
                        Toast.makeText(this@CreatingActivity, "已取消发布", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@CreatingActivity, ex.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })

            Toast.makeText(this,"已取消发布", Toast.LENGTH_SHORT).show()
            cancel.isEnabled=false
            cancel.setText("已取消")
            val intent = Intent(this, CreatingListActivity::class.java)
            startActivity(intent)
            intent.putExtra("user_id",userId)
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
            finish()
        }
        finish.setOnClickListener {
            var mission=MissionTable()
            mission.objectId=misId
            mission.completeC="true"
            mission.update(object :UpdateListener(){
                override fun done(ex: BmobException?) {
                    if (ex == null) {
                        Toast.makeText(this@CreatingActivity, "已确认完成", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@CreatingActivity, ex.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
            val intent = Intent(this, CreatingListActivity::class.java)
            startActivity(intent)
            intent.putExtra("user_id",userId)
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
            finish()
        }
        back.setOnClickListener {
            val intent = Intent(this, CreatingListActivity::class.java)
            intent.putExtra("user_id",userId)
            startActivity(intent)
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
            finish()
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val intent = Intent(this, CreatingListActivity::class.java)
        intent.putExtra("user_id",userId1)
        startActivity(intent)
        overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
        finish()
        return super.onKeyDown(keyCode, event)
    }
}
