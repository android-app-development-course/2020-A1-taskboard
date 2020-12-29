package com.example.taskboard
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.ArrayAdapter
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import kotlinx.android.synthetic.main.activity_creating_list.*
import java.util.ArrayList

class CreatingListActivity : Activity() {

    private  var data= ArrayList<String>()
    private  var data2= ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creating_list)
        val userId=intent.getStringExtra("user_id")
        var bmobQuery: BmobQuery<MissionTable> = BmobQuery()
        bmobQuery.addWhereEqualTo("completeC","false")
        bmobQuery.addWhereEqualTo("publisherID",userId)
        bmobQuery.findObjects(object : FindListener<MissionTable>() {
            override fun done(missions: MutableList<MissionTable>?, ex: BmobException?) {
                if (ex == null) {
                    Toast.makeText(this@CreatingListActivity, "查询成功", Toast.LENGTH_SHORT).show()
                    val adapter= ArrayAdapter(this@CreatingListActivity,R.layout.list_item,data)
                    MissionList.adapter=adapter
                    if (missions != null) {
                        for (mission: MissionTable in missions) {
                            data.add(mission.title.toString())
                            data2.add(mission.objectId)
                        }
                    }
                } else {
                    Toast.makeText(this@CreatingListActivity, ex.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
        val adapter= ArrayAdapter(this, R.layout.list_item,data)
        MissionList.adapter=adapter
        MissionList.setOnItemClickListener { _, _, position, _ ->
            val missionId=data2[position]//任务的的信息 有其构成的数据组成
            val intent= Intent(this, CreatingActivity::class.java)
            intent.putExtra("user_id",userId)
            intent.putExtra("mission_id",missionId)
            startActivity(intent)
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
            finish()
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
