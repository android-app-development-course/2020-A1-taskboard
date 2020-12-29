package com.example.taskboard

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Bmob.initialize(this,"a8dcba2f3419108f8046570cd0ca5874")
        button2.setOnClickListener {
            //用户输入账号密码
            val account=editText2.text.toString()
            val password=editText3.text.toString()
            //Bmob提供了一个专门的用户类——BmobUser来自动处理用 户账户管理所需的功能。setUsername  setPassword(）两个方法
            var user = BmobUser()
            user.setUsername(account)
            user.setPassword(password)
            if (account == ""||password=="") {
                Toast.makeText(this, "请输入用户名密码或注册账号", Toast.LENGTH_SHORT).show()
            }
            else
            {
                user.login(object : SaveListener<User>() {
                    override fun done(currentUser: User?, ex: BmobException?) {
                        if (ex == null) {
                            Toast.makeText(this@LoginActivity, "登录成功", Toast.LENGTH_LONG).show()
                            val intent=Intent(this@LoginActivity,choose::class.java)
                            intent.putExtra("user_id",user.objectId)
                            intent.putExtra("user_name",account)
                            startActivity(intent)
                            finish()
                            overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
                        } else {//ex.message 是密码错误，请检查
                            Toast.makeText(this@LoginActivity, ex.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }

        button3.setOnClickListener{
           //转去注册
            val intent = Intent(this, ZhuceActivity::class.java)
            startActivity(intent)
            //finish()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        AlertDialog.Builder(this).apply {
            setTitle("确认退出")
            setMessage("您确定要退出吗？")
            setPositiveButton("退出")
            {
                    _, _ ->
                finish()
            }
            setNegativeButton("取消")
            {
                    _, _ ->
            }
            show()
        }
        return super.onKeyDown(keyCode, event)
    }
}
