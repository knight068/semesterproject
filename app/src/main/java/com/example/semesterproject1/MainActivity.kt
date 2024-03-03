package com.example.semesterproject1

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var logInBtn : Button
    private lateinit var editUser: EditText
    private lateinit var editPassword: EditText
    private lateinit var dbh:DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {

        //  logInBtn=findViewById(R.id.btn_logIn)
        // editUser=findViewById(R.id.etLogInEmail)
        // editPassword=findViewById(R.id.etLogInPassowrd)
        dbh= DBHelper(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnTakeToRegister1 =findViewById<Button>(R.id.btnTakeToRegister)
        var btn_logIn1 =findViewById<Button>(R.id.btn_logIn)
        btnTakeToRegister1.setOnClickListener {
            val intent = Intent(this,registerActivity::class.java)
            startActivity(intent)
        }
        btn_logIn1.setOnClickListener {
            checkLogIN()
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
                val intent = Intent(this,MainScreen::class.java)
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
}

