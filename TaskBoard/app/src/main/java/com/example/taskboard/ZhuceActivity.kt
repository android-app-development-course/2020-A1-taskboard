package com.example.taskboard

import android.os.Bundle
import android.widget.Toast
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import kotlinx.android.synthetic.main.activity_zhuce.*
//表名是你  类的名字 objectid  a2dd965696自动	   （比目	89 类的属性）	（2020-12-18 16:49:01	2020-12-18 16:49:01）

class ZhuceActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zhuce)
        Bmob.initialize(this,"a8dcba2f3419108f8046570cd0ca5874")
        btn_save.setOnClickListener {
            var user = BmobUser()
            user.setUsername (et_phone.text.toString())
            user.setPassword(et_email.text.toString())
            user.signUp(object : SaveListener<User>() {
                override fun done(currentUser: User?, ex: BmobException?) {
                    if (ex == null) {
                        Toast.makeText(this@ZhuceActivity, "注册成功", Toast.LENGTH_LONG).show()
                        finish()//成功就退出注册界面
                    } else {
                       Toast.makeText(this@ZhuceActivity, ex.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
        back.setOnClickListener {
            finish()
        }
    }

}