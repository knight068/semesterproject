package com.example.semesterproject1

import android.app.AlertDialog
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.semesterproject1.storage.SharedPrefManager
import de.hdodenhof.circleimageview.CircleImageView
import myRecycler.ClothRecycler
import myRecycler.ElectricityRecycler
import myRecycler.FoodRecycler
import myRecycler.InternetRecycler
import myRecycler.OtherRecycler
import myRecycler.PhoneRecycler
import myRecycler.TipsAndTricks
import myRecycler.UserReminders
import myRecycler.WaterRecycler
import myRecycler.myRecyclerActivity

class MainScreen : AppCompatActivity() {
    //private val PICK_IMAGE_REQUEST = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        var userId =SharedPrefManager.getInstance(applicationContext).user!!._id
        Log.i("userId",userId!!)
        bindUser()


        bindRecyclers()
        bindBottomNavigation()
        bindUserAndNotifcation()

    }

    private fun bindUserAndNotifcation() {
        var llProfile = findViewById<LinearLayout>(R.id.llProfile)
        llProfile.setOnClickListener {
            val intent:Intent=Intent(this,ProfileEdit::class.java)
            startActivity(intent)
        }
        val ciReminders=findViewById<CircleImageView>(R.id.ciReminders)
        ciReminders.setOnClickListener {
            val intent=Intent(this,UserReminders::class.java)
            startActivity(intent)
        }
    }

    private fun bindBottomNavigation() {
        var ibBudget :ImageButton=findViewById(R.id.ibBudget)
        ibBudget.setOnClickListener{
            val intent:Intent=Intent(this,Budget::class.java)
            startActivity(intent)
        }
        var ibProfile :ImageButton=findViewById(R.id.ibProfile)
        ibProfile.setOnClickListener{
            val intent:Intent=Intent(this,ProfileEdit::class.java)
            startActivity(intent)
        }
        var ibTips :ImageButton=findViewById(R.id.ibTips)
        ibTips.setOnClickListener{
            val intent:Intent=Intent(this,TipsAndTricks::class.java)
            startActivity(intent)
        }
        var ibReminders :ImageButton=findViewById(R.id.ibReminders)
        ibReminders.setOnClickListener{
            val intent:Intent=Intent(this,Reminders::class.java)
            startActivity(intent)
        }

    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        var ivProfile = findViewById<ImageView>(R.id.ivProfile)
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
//            val imageUri = data.data
//            val imageStream = contentResolver.openInputStream(imageUri!!)
//            val selectedImage = BitmapFactory.decodeStream(imageStream)
//            val id = System.currentTimeMillis()
//            // val name = "something"
//            ivProfile.setImageURI(imageUri)
//        }
//    }

//    private fun profileUpdate() {
//         /*btnCancel = findViewById(R.id.btnCancel)
//         btnAdd = findViewById(R.id.btnAdd)
//         tvUserName = findViewById(R.id.tvUserName)
//         etAddNewUserName = findViewById(R.id.etAddNewUserName)*/
//        val view = View.inflate(this, R.layout.update_profile_dialog, null)
//        val builder = AlertDialog.Builder(this)
//        builder.setView(view)
//        val dialog = builder.create()
//        dialog.show()
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        val btnCancel = view.findViewById<Button>(R.id.btnCancel)
//        var btnAdd =view.findViewById<Button>(R.id.btnAdd)
//        var tvUserName = findViewById<TextView>(R.id.tvUserName)
//        val etAddNewUserName =view.findViewById<EditText>(R.id.etAddNewUserName)
//
//        btnCancel.setOnClickListener {
//            dialog.dismiss()
//        }
//        btnAdd.setOnClickListener{
//            tvUserName.text=etAddNewUserName.text
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.type = "image/*"
//            startActivityForResult(intent, PICK_IMAGE_REQUEST)
//            etAddNewUserName.text.clear()
//            dialog.dismiss()
//        }
//    }



    private fun bindRecyclers(){
        var llWater = findViewById<LinearLayout>(R.id.llWater)
        var llPhone = findViewById<LinearLayout>(R.id.llPhone)
        var llOther =findViewById<LinearLayout>(R.id.llOther)
        var llFood =findViewById<LinearLayout>(R.id.llFood)
        var llCloth=findViewById<LinearLayout>(R.id.llClothes)
        var llElectricity = findViewById<LinearLayout>(R.id.llElectricity)
        llElectricity.setOnClickListener{
            val intent= Intent(this, ElectricityRecycler::class.java)
            startActivity(intent)
        }
        var llInternet :LinearLayout=findViewById(R.id.llInternet)
        llInternet.setOnClickListener{
            val intent= Intent(this, InternetRecycler::class.java)
            startActivity(intent)
        }
       llWater.setOnClickListener{
           val intent=Intent(this,WaterRecycler::class.java)
           startActivity(intent)
       }
        llPhone.setOnClickListener{
               val intent=Intent(this,PhoneRecycler::class.java)
               startActivity(intent)
        }
        llCloth.setOnClickListener{
            val intent=Intent(this,ClothRecycler::class.java)
            startActivity(intent)
        }
        llFood.setOnClickListener{
            val intent=Intent(this,FoodRecycler::class.java)
            startActivity(intent)
        }
        llOther.setOnClickListener{
            val intent=Intent(this,OtherRecycler::class.java)
            startActivity(intent)
        }
    }
    fun bindUser(){
        var tvUser =findViewById<TextView>(R.id.tvUserName)
        tvUser.text=SharedPrefManager.getInstance(applicationContext).user!!.name.toString()
    }
}