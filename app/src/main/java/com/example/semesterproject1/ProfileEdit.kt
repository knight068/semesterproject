package com.example.semesterproject1

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.example.semesterproject1.storage.SharedPrefManager
import de.hdodenhof.circleimageview.CircleImageView
import myRecycler.TipsAndTricks

class ProfileEdit : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)
        bindBottomNavigation()
        var circleProfilePicture=findViewById<CircleImageView>(R.id.ci_Profile)
        val btnGoToReport:Button=findViewById(R.id.btnGoToReport)
        btnGoToReport.setOnClickListener {
            val intent=Intent(this,CreatingReport::class.java)
            startActivity(intent)
        }

        val btnLogOut :Button=findViewById(R.id.btn_LogOut)
        btnLogOut.setOnClickListener {
            logOut()
        }

        circleProfilePicture.setOnClickListener {
            pickNewImage()
        }

    }

    private fun logOut() {
        SharedPrefManager.getInstance(applicationContext).clear()
        intent=Intent(this,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


    private fun bindBottomNavigation() {
        var ibBudget : ImageButton =findViewById(R.id.ibBudget)
        ibBudget.setOnClickListener{
            val intent:Intent=Intent(this,Budget::class.java)
            startActivity(intent)
        }
       var ibBills:ImageButton=findViewById(R.id.ibBills)
        ibBills.setOnClickListener{
            val intent:Intent=Intent(this, MainScreen::class.java)
            startActivity(intent)

        }
        var ibTips : ImageButton =findViewById(R.id.ibTips)
        ibTips.setOnClickListener{
            val intent:Intent=Intent(this, TipsAndTricks::class.java)
            startActivity(intent)
        }
        var ibReminders :ImageButton=findViewById(R.id.ibReminders)
        ibReminders.setOnClickListener{
            val intent:Intent=Intent(this,Reminders::class.java)
            startActivity(intent)
        }

    }

    private fun pickNewImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var circleProfilePicture=findViewById<CircleImageView>(R.id.ci_Profile)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data
            val imageStream = contentResolver.openInputStream(imageUri!!)
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            val id = System.currentTimeMillis()
            // val name = "something"
            circleProfilePicture.setImageURI(imageUri)
        }
    }
}