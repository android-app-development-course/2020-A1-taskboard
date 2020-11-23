package com.example.taskboard
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.taskboard.BaseActivity
import com.example.taskboard.R
import kotlinx.android.synthetic.main.activity_zhuce.*

class ZhuceActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zhuce)
        val prefs= getPreferences(Context.MODE_PRIVATE)
        btn_save.setOnClickListener {
            val account=et_phone.text.toString()
            val password=et_email.text.toString()
            val editor=prefs.edit()
            editor.putString("account",account)
            editor.putString("password",password)
            editor.apply()
            Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show()
            finish()
        }
    }



}