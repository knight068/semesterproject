package com.example.semesterproject1

import android.animation.ObjectAnimator
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.semesterproject1.api.RetrofitClient
import com.example.semesterproject1.models.BudgetUpdateResponse
import com.example.semesterproject1.models.GetBudgetResponse
import com.example.semesterproject1.models.LogInResponse
import com.example.semesterproject1.models.NetBillsResponse
import com.example.semesterproject1.storage.SharedPrefManager
import myRecycler.TipsAndTricks
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Budget : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget)
        val userId=SharedPrefManager.getInstance(applicationContext).user!!._id
        bindBudget()
        progressBarBind()
        bindBottomNavigation()
        var tvStartBudgetDate : TextView= findViewById(R.id.tvStartBudgetDate)
        var tvEndBudgetDate:TextView=findViewById(R.id.tvEndBudgetDate)
        var btnSaveBudget : Button=findViewById(R.id.btnSaveBudget)
        var etBudgetTotalAmount : EditText=findViewById(R.id.etBudgetTotalAmount)
        val btnUpdateBudget:Button=findViewById(R.id.btnUpdateBudget)
        val calendar = Calendar.getInstance()
        tvStartBudgetDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    tvStartBudgetDate.text = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(calendar.time)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            // Show the DatePickerDialog
            datePickerDialog.show()
        }
        tvEndBudgetDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    tvEndBudgetDate.text = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(calendar.time)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            // Show the DatePickerDialog
            datePickerDialog.show()
        }
        btnSaveBudget.setOnClickListener {
            if (tvEndBudgetDate.text.toString()!=null && tvStartBudgetDate.text.toString()!=null
                && etBudgetTotalAmount.text.toString()!=null){
                Toast.makeText(this,"the budget has been created\ntotal amount is "
                        +etBudgetTotalAmount.text.toString()+"\nstart and end date is "+tvStartBudgetDate.text.toString()+"\n"
                +tvEndBudgetDate.text.toString(),Toast.LENGTH_LONG).show()
            }
        }
        val btnSave:Button=findViewById(R.id.btnSaveBudget)
        btnSave.setOnClickListener {
            val tvStartBudgetDate:TextView=findViewById(R.id.tvStartBudgetDate)
            val tvEndBudgetDate:TextView=findViewById(R.id.tvEndBudgetDate)
            val etBudgetTotalAmount:EditText=findViewById(R.id.etBudgetTotalAmount)
            var startDate=tvStartBudgetDate.text.toString().trim()
            var endDate=tvEndBudgetDate.text.toString().trim()
            var budgetAmount=etBudgetTotalAmount.text.toString().toFloat()
            val userId=SharedPrefManager.getInstance(applicationContext).user!!._id
            val mProgressDialog = ProgressDialog(this)
            mProgressDialog.setTitle("Please wait")
//            mProgressDialog.setMessage("This is MESSAGE")
            mProgressDialog.show()

            RetrofitClient.instance.updateBudget(userId!!,budgetAmount, startDate,endDate).enqueue(object :
                Callback<BudgetUpdateResponse>{
                override fun onResponse(
                    call: Call<BudgetUpdateResponse>,
                    response: Response<BudgetUpdateResponse>
                ) {
                    Toast.makeText(applicationContext,response.body()!!.message,Toast.LENGTH_SHORT).show()
                    mProgressDialog.dismiss()
                }

                override fun onFailure(call: Call<BudgetUpdateResponse>, t: Throwable) {
                    Toast.makeText(applicationContext,"Failed",Toast.LENGTH_SHORT).show()
                }

            })
        }
        btnUpdateBudget.setOnClickListener {
            val budgetAmmount : TextView=findViewById(R.id.budgetAmmount)
            val budgetTitle :TextView=findViewById(R.id.budgetTitle)
            val tvStartBudgetDate=findViewById<TextView>(R.id.tvStartBudgetDate)
            val tvEndBudgetDate:TextView=findViewById(R.id.tvEndBudgetDate)
            val etBudgetTotalAmount:EditText=findViewById(R.id.etBudgetTotalAmount)
            val btnSaveBudget:Button=findViewById(R.id.btnSaveBudget)
            val btnUpdateBudget:Button=findViewById(R.id.btnUpdateBudget)
            val currnetBudgetStart:TextView=findViewById(R.id.currnetBudgetStart)
            val currentBudgetEnd=findViewById<TextView>(R.id.currentBudgetEnd)


            budgetTitle.visibility=View.GONE
            budgetAmmount.visibility=View.GONE
            tvStartBudgetDate.visibility=View.VISIBLE
            tvEndBudgetDate.visibility=View.VISIBLE
            etBudgetTotalAmount.visibility=View.VISIBLE
            btnSaveBudget.visibility=View.VISIBLE
            btnUpdateBudget.visibility=View.GONE
            currnetBudgetStart.visibility=View.GONE
            currentBudgetEnd.visibility=View.GONE
            btnSaveBudget.setOnClickListener {
                val userId=SharedPrefManager.getInstance(applicationContext).user!!._id
                var padgetval=etBudgetTotalAmount.text.toString().toFloat()
                var startDate =tvStartBudgetDate.text.toString()
                var endDate=tvEndBudgetDate.text.toString()
                val mProgressDialog = ProgressDialog(this)
                mProgressDialog.setTitle("Please wait")
//            mProgressDialog.setMessage("This is MESSAGE")
                mProgressDialog.show()


                RetrofitClient.instance.updateBudget(userId!!,padgetval,startDate,endDate).enqueue(object : Callback<BudgetUpdateResponse>{
                    override fun onResponse(
                        call: Call<BudgetUpdateResponse>,
                        response: Response<BudgetUpdateResponse>
                    ) {
                        var l =response.body()
                        Log.i("budgetUpdate",l.toString())
                        Toast.makeText(applicationContext,response.body()!!.message.toString(),Toast.LENGTH_SHORT).show()
                        recreate()
                    }

                    override fun onFailure(call: Call<BudgetUpdateResponse>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message.toString(),Toast.LENGTH_SHORT).show()
                    }

                } )
//                val intent=Intent(this,MainScreen::class.java)
//                startActivity(intent)


            }
        }

    }

    private fun bindBudget() {
        if (SharedPrefManager.getInstance(application).user!!.padget!=0f){
            val budgetAmmount : TextView=findViewById(R.id.budgetAmmount)
            val budgetTitle :TextView=findViewById(R.id.budgetTitle)
            val tvStartBudgetDate=findViewById<TextView>(R.id.tvStartBudgetDate)
            val tvEndBudgetDate:TextView=findViewById(R.id.tvEndBudgetDate)
            val etBudgetTotalAmount:EditText=findViewById(R.id.etBudgetTotalAmount)
            val btnSaveBudget:Button=findViewById(R.id.btnSaveBudget)
            val btnUpdateBudget:Button=findViewById(R.id.btnUpdateBudget)
            val currnetBudgetStart:TextView=findViewById(R.id.currnetBudgetStart)
            val currentBudgetEnd=findViewById<TextView>(R.id.currentBudgetEnd)


            val userId=SharedPrefManager.getInstance(applicationContext).user!!._id
            val mProgressDialog = ProgressDialog(this)
            mProgressDialog.setTitle("Please wait")
//            mProgressDialog.setMessage("This is MESSAGE")
            mProgressDialog.show()

            RetrofitClient.instance.getBudget(userId!!).enqueue(object : Callback<GetBudgetResponse>{
                override fun onResponse(
                    call: Call<GetBudgetResponse>,
                    response: Response<GetBudgetResponse>
                ) {
                    val l=response.body()
                    Log.i("Budget",l.toString())
                    budgetAmmount.text=response.body()!!.userBudget.padget.toString()
                    currnetBudgetStart.text="Budget Start Date:\n"+response.body()!!.userBudget.StartDatePadget
                    currentBudgetEnd.text="Budget End Date:\n"+response.body()!!.userBudget.FinalDatePadget
                    mProgressDialog.dismiss()
                }

                override fun onFailure(call: Call<GetBudgetResponse>, t: Throwable) {
                    Toast.makeText(applicationContext,t.message.toString(),Toast.LENGTH_SHORT).show()
                }
            })

            budgetTitle.visibility=View.VISIBLE
            budgetAmmount.visibility=View.VISIBLE
            tvStartBudgetDate.visibility=View.GONE
            tvEndBudgetDate.visibility=View.GONE
            etBudgetTotalAmount.visibility=View.GONE
            btnSaveBudget.visibility=View.GONE
            btnUpdateBudget.visibility=View.VISIBLE
            currnetBudgetStart.visibility=View.VISIBLE
            currentBudgetEnd.visibility=View.VISIBLE



        }
    }

    private fun bindBottomNavigation() {
        var ibBills : ImageButton =findViewById(R.id.ibBills)
        ibBills.setOnClickListener{
            val intent: Intent = Intent(this,MainScreen::class.java)
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
        var ibReminders : ImageButton =findViewById(R.id.ibReminders)
        ibReminders.setOnClickListener{
            val intent: Intent = Intent(this,Reminders::class.java)
            startActivity(intent)
        }

    }

    private fun progressBarBind() {
        var pbBudget: ProgressBar=findViewById(R.id.pbBudget)
        var etBudgetTotalAmount = findViewById<EditText>(R.id.etBudgetTotalAmount)
        if(etBudgetTotalAmount!=null) {
            pbBudget.visibility=View.VISIBLE
            //var currentBudget= etBudgetTotalAmount.text.toString().toInt()
            pbBudget.max = 500
            pbBudget.progress = 300
            pbBudget.progressTintList = ColorStateList.valueOf(Color.CYAN)
//       var pbAnimator = ObjectAnimator.ofInt(pbBudget,"pbBudget",currentProgress,500)
//            pbAnimator.duration=2000
//        pbAnimator.start()}
        }
    }
}