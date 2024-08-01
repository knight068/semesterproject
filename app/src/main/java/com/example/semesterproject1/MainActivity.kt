package com.example.semesterproject1

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.semesterproject1.api.RetrofitClient
import com.example.semesterproject1.models.LogInResponse
import com.example.semesterproject1.storage.SharedPrefManager
import myRecycler.AddingToRecyclerTest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private lateinit var dbh:DBHelper
    private val baseUri ="https://graduate-final-1.onrender.com/api/"
    override fun onCreate(savedInstanceState: Bundle?) {

        //  logInBtn=findViewById(R.id.btn_logIn)
        // editUser=findViewById(R.id.etLogInEmail)
        // editPassword=findViewById(R.id.etLogInPassowrd)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onStart()
        var btnTakeToRegister1 =findViewById<Button>(R.id.btnTakeToRegister)
        var btn_logIn1 =findViewById<Button>(R.id.btn_logIn)

        btnTakeToRegister1.setOnClickListener {
            val intent = Intent(this,registerActivity::class.java)
            startActivity(intent)
        }


        btn_logIn1.setOnClickListener {

            var etLogInEmail :EditText=findViewById(R.id.etLogInEmail)
            var etLogInPassword :EditText=findViewById(R.id.etLogInPassword)
            val email=etLogInEmail.text.toString().trim()
            val password =etLogInPassword.text.toString().trim()


            RetrofitClient.instance.logInUser(email,password).enqueue(object :Callback<LogInResponse>{
                override fun onResponse(
                    call: Call<LogInResponse>,
                    response: Response<LogInResponse>
                ) {
                    var l = response.body()
                    Log.i("data",l.toString())
                    Toast.makeText(applicationContext,l.toString(),Toast.LENGTH_LONG).show()
                    SharedPrefManager.getInstance(applicationContext).saveUser(response.body()?.data!!.userr)
                    val intent =Intent(applicationContext,MainScreen::class.java)
                    intent.flags =Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_CLEAR_TASK
                   startActivity(intent)

                }

                override fun onFailure(call: Call<LogInResponse>, t: Throwable) {
                    Toast.makeText(applicationContext,t.message.toString(),Toast.LENGTH_SHORT).show()
                }

            })



           // checkLogIN()
        }
    }

    private fun checkLogIN() {
        var etLogInEmail1 = findViewById<EditText>(R.id.etLogInEmail)
        var etLogInPassowrd = findViewById<EditText>(R.id.etLogInPassowrd)
        var etLogInEmail = findViewById<EditText>(R.id.etLogInEmail)
        if (etLogInEmail1.text.isEmpty()||etLogInPassowrd.text.isEmpty()){
            alertFail("Email and Password are Required.")
        }else{
            val userTXT=etLogInEmail.text.toString()
            val passwordTXT=etLogInPassowrd.text.toString()
            val checkUser =dbh.checkUserPassword(userTXT,passwordTXT)
            if (checkUser==true){


                sendLogIn()
                val intent = Intent(this,AddingToRecyclerTest::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"wrong passowrd or email ", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun sendLogIn() {
        Toast.makeText(this,"send", Toast.LENGTH_SHORT).show()
    }

    private fun alertFail(s: String) {
        AlertDialog.Builder(this)
            .setTitle("Failed")
            .setIcon(R.drawable.ic_warning)
            .setMessage(s)
            .setPositiveButton("ok", DialogInterface.OnClickListener(){
                    dialog, which ->  dialog.dismiss()
            }).show()

    }

    override fun onStart() {
        super.onStart()
         if(SharedPrefManager.getInstance(applicationContext).isLoggedIn){
             val intent =Intent(applicationContext,MainScreen::class.java)
             startActivity(intent)

         }
    }
}

