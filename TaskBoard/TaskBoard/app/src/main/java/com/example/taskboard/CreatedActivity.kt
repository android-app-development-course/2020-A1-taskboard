package com.example.taskboard
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_created.*

class CreatedActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_created)
        back.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.overturn_in,R.anim.overturn_out)
        }
    }

}
