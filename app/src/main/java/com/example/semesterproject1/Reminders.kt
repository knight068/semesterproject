package com.example.semesterproject1

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.semesterproject1.api.RetrofitClient
import com.example.semesterproject1.models.BudgetUpdateResponse
import com.example.semesterproject1.models.RemindersAddResponse
import com.example.semesterproject1.storage.SharedPrefManager
import myRecycler.TipsAndTricks
import myRecycler.UserReminders
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Reminders : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminders)
        bindBottomNavigation()
        datePickerFun()
        bindButtons()

    }

    private fun bindButtons() {
        val btnSaveReminders=findViewById<Button>(R.id.btnSaveReminders)
        val btnGoToReminders=findViewById<Button>(R.id.btnGoToReminders)
        btnGoToReminders.setOnClickListener {
            var intent=Intent(applicationContext,UserReminders::class.java)
            startActivity(intent)
        }
        btnSaveReminders.setOnClickListener {
            var tvRemindersDate:TextView=findViewById(R.id.tvRemindersDate)
            var etReminderDetails:EditText=findViewById(R.id.etReminderDetails)
            var reminderDes=etReminderDetails.text.toString().trim()
            var reminderDate=tvRemindersDate.text.toString().trim()
            var userid=SharedPrefManager.getInstance(applicationContext).user!!._id
            RetrofitClient.instance.postReminders(userid!!,reminderDes,reminderDate).enqueue(object :
                Callback<RemindersAddResponse>{
                override fun onResponse(
                    call: Call<RemindersAddResponse>,
                    response: Response<RemindersAddResponse>
                ) {
                    var l = response.body().toString()
                    Log.i("reminders",l)
                }

                override fun onFailure(call: Call<RemindersAddResponse>, t: Throwable) {
                   Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    private fun datePickerFun() {
        val calendar = Calendar.getInstance()
        val tvRemindersDate = findViewById<TextView>(R.id.tvRemindersDate)
        tvRemindersDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    tvRemindersDate.text =
                        SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(calendar.time)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            // Show the DatePickerDialog
            datePickerDialog.show()
        }
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