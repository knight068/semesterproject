package com.example.semesterproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import myRecycler.TipsAndTricks

class Reminders : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminders)
        bindBottomNavigation()
    }
    private fun bindBottomNavigation() {
        var ibBudget : ImageButton =findViewById(R.id.ibBudget)
        ibBudget.setOnClickListener{
            val intent: Intent = Intent(this,Budget::class.java)
            startActivity(intent)
        }
        var ibProfile : ImageButton =findViewById(R.id.ibProfile)
        ibProfile.setOnClickListener{
            val intent: Intent = Intent(this,ProfileEdit::class.java)
            startActivity(intent)
        }
        var ibTips : ImageButton =findViewById(R.id.ibTips)
        ibTips.setOnClickListener{
            val intent: Intent = Intent(this, TipsAndTricks::class.java)
            startActivity(intent)
        }
        var ibBills : ImageButton =findViewById(R.id.ibBills)
        ibBills.setOnClickListener{
            val intent: Intent = Intent(this,MainScreen::class.java)
            startActivity(intent)
        }

    }
}