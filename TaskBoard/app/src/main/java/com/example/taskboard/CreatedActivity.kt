package com.example.taskboard
import android.app.Activity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.KeyEvent
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import kotlinx.android.synthetic.main.activity_created.*
import kotlinx.android.synthetic.main.activity_created.back
import kotlinx.android.synthetic.main.activity_created.xiangqing
import kotlinx.android.synthetic.main.activity_created.mission_name
import kotlinx.android.synthetic.main.activity_created.textView13
import kotlinx.android.synthetic.main.activity_created.textView15


class CreatedActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_created)
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
                    }
                }
            }
        })

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
