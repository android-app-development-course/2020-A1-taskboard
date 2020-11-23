package com.example.taskboard



import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val prefs= getSharedPreferences("ZhuceActivity", Context.MODE_PRIVATE)
        button2.setOnClickListener {
            val account = prefs.getString("account","")
            val password = prefs.getString("password","")
            val account2=editText2.text.toString()
            val password2=editText3.text.toString()
            // 濡傛灉璐﹀彿鏄痑dmin涓斿瘑鐮佹槸123456锛屽氨璁や负鐧诲綍鎴愬姛
            if (account == ""&& password=="") {
                Toast.makeText(this, "请输入用户名密码或注册账号", Toast.LENGTH_SHORT).show()
            }
            else if (account==account2&&password==password2){
                val intent = Intent(this, choose::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
            }
            else{
                Toast.makeText(this,"密码错误，请检查",Toast.LENGTH_SHORT).show()
            }
        }
        button3.setOnClickListener{
            val intent = Intent(this, ZhuceActivity::class.java)
            startActivity(intent)
            //finish()
        }
    }
}
