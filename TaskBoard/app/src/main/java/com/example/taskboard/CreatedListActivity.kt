package com.example.taskboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.ArrayAdapter
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import kotlinx.android.synthetic.main.activity_created_list.MissionList
import kotlinx.android.synthetic.main.activity_created_list.back
import java.util.ArrayList


class CreatedListActivity : Activity() {

    private  var data= ArrayList<String>()
    private  var data2= ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_created_list)
        val userId=intent.getStringExtra("user_id")
        var bmobQuery: BmobQuery<MissionTable> = BmobQuery()
        bmobQuery.addWhereEqualTo("publisherID",userId)
        bmobQuery.addWhereEqualTo("completeC","true")
        bmobQuery.findObjects(object : FindListener<MissionTable>() {
            override fun done(missions: MutableList<MissionTable>?, ex: BmobException?) {

                if (ex == null) {
                    Toast.makeText(this@CreatedListActivity, "查询成功", Toast.LENGTH_LONG).show()

                    val adapter= ArrayAdapter(this@CreatedListActivity,R.layout.list_item,data)
                    MissionList.adapter=adapter

                    if (missions != null) {
                        for (mission: MissionTable in missions) {
                            Log.e("aaaaaaaaa", mission.title.toString())
                            data.add(mission.title.toString())
                            data2.add(mission.objectId.toString())
                            Log.e("aaaaaaaaa", mission.title.toString())
                        }

                    }
                } else {
                    Toast.makeText(this@CreatedListActivity, ex.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        MissionList.setOnItemClickListener { _, _, position, _ ->
            val which_mission = data2[position]//任务的的信息 有其构成的数据组成   data2 任务id
            val intent= Intent(this,CreatedActivity::class.java)
            intent.putExtra("mission_id", which_mission)
            startActivity(intent)
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
        }

        back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.left_tran_in,R.anim.left_tran_out)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        finish()
        overridePendingTransition(R.anim.left_tran_in,R.anim.left_tran_out)
        return super.onKeyDown(keyCode, event)
    }
}
