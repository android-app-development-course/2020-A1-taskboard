package com.example.taskboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_receiving_list.*

class ReceivingListActivity : Activity() {
    private val data=listOf("任务一","任务二","任务三","任务四","任务五","任务六","任务一","任务二","任务三","任务四","任务五","任务六")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiving_list)
        val adapter= ArrayAdapter(this,R.layout.list_item,data)
        MissionList.adapter=adapter
        MissionList.setOnItemClickListener { parent, view, position, id ->
            val which_misson = data[position]//任务的的信息 有其构成的数据组成
            val intent= Intent(this,ReceivingActivity::class.java)
            intent.putExtra("misson", which_misson)
            startActivity(intent)
        }
        back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.left_tran_in,R.anim.left_tran_out)
        }
    }
}
