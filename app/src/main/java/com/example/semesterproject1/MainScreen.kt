package com.example.semesterproject1

import android.app.AlertDialog
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import myRecycler.myRecyclerActivity

class MainScreen : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        var llProfile = findViewById<LinearLayout>(R.id.llProfile)
        bindRecyclers()
        llProfile.setOnClickListener {
            profileUpdate()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var ivProfile = findViewById<ImageView>(R.id.ivProfile)
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data
            val imageStream = contentResolver.openInputStream(imageUri!!)
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            val id = System.currentTimeMillis()
            // val name = "something"
            ivProfile.setImageURI(imageUri)
        }
    }

    private fun profileUpdate() {
        var btnCancel = findViewById<Button>(R.id.btnCancel)
        var btnAdd = findViewById<Button>(R.id.btnAdd)
        var tvUserName = findViewById<TextView>(R.id.tvUserName)
        var etAddNewUserName = findViewById<EditText>(R.id.etAddNewUserName)
        val view = View.inflate(this, R.layout.update_profile_dialog, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        btnAdd.setOnClickListener{
            tvUserName.text=etAddNewUserName.text
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
            etAddNewUserName.text.clear()
            dialog.dismiss()
        }
    }



    private fun bindRecyclers(){
        /*var llWater = findViewById<LinearLayout>(llWater)
        var llPhone = findViewById<LinearLayout>(llPhone)
        var llInternet = findViewById<LinearLayout>(llInternet)
        var llOther =findViewById<LinearLayout>(llOther)*/
        var llElectricity = findViewById<LinearLayout>(R.id.llElectricity)
        llElectricity.setOnClickListener{
            val intent= Intent(this, myRecyclerActivity::class.java)
            startActivity(intent)
        }
        /* llWater.setOnClickListener{
             val intent= Intent(this,WaterActivity::class.java)
             startActivity(intent)
         }
         llPhone.setOnClickListener{
             val intent= Intent(this,PhoneRecycler::class.java)
             startActivity(intent)
         }
         llInternet.setOnClickListener{
             val intent= Intent(this,InternetRecycler::class.java)
             startActivity(intent)
         }
         llOther.setOnClickListener{
             val intent= Intent(this,OtherRecycler::class.java)
             startActivity(intent)
         }*/
    }
}