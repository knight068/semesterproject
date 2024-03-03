package com.example.semesterproject1

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
private lateinit var db: DBHelper

class registerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        db = DBHelper(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var btnTakeToLogIn = findViewById<Button>(R.id.btnTakeToLogIn)
        var btnRegister = findViewById<Button>(R.id.btnRegister)

        btnTakeToLogIn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        btnRegister.setOnClickListener {
            checkRegister()

        }
    }

    private fun checkRegister() {
        var etRegisterId = findViewById<EditText>(R.id.etRegisterId)
        var etRegisterEmail = findViewById<EditText>(R.id.etRegisterEmail)
        var etRegisterPassword = findViewById<EditText>(R.id.etRegisterPassword)
        var etRegisterUserName = findViewById<EditText>(R.id.etRegisterUserName)
        var etReInterPassword = findViewById<EditText>(R.id.etReInterPassword)
        if (etRegisterId.text.isEmpty()||etRegisterEmail.text.isEmpty()||etRegisterPassword.text.isEmpty()
            ||etRegisterUserName.text.isEmpty()){
            alertFail("Username ,National Id, Email, Password  are required")
        }else if ( !etRegisterPassword.text.toString().equals(etReInterPassword.text.toString())){
            alertFail("Password and Confirm Password doesn't match")
        }else{
            sendRegister()
        }
    }

    private fun sendRegister() {
        var etRegisterEmail = findViewById<EditText>(R.id.etRegisterEmail)
        var etRegisterPassword = findViewById<EditText>(R.id.etRegisterPassword)
        var etReInterPassword = findViewById<EditText>(R.id.etReInterPassword)
        val userEmailTXT =etRegisterEmail.text.toString()
        val userPasswordTXT = etRegisterPassword.text.toString()
        val ReInterPasswordTxt = etReInterPassword.text.toString()
        val saveData =db.insertData(userEmailTXT,userPasswordTXT)
        if (saveData==true){
            alertSuccess("Register is successful ")}
        else{
            Toast.makeText(this,"user already exist", Toast.LENGTH_SHORT).show()
        }
    }

    private fun alertSuccess(s: String) {
        AlertDialog.Builder(this)
            .setTitle("Success")
            .setIcon(R.drawable.ic_check)
            .setMessage(s)
            .setPositiveButton("LogIn", DialogInterface.OnClickListener(){
                    dialog, which ->  onBackPressed()
            }).show()
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