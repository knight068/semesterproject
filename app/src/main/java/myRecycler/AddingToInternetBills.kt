package myRecycler

import android.app.Activity
import android.app.DatePickerDialog
import android.content.ContentResolver
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import com.example.semesterproject1.R
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddingToInternetBills : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var imageUriTemp:Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_to_internet_bills)
        var btnAddInternetBill :Button=findViewById(R.id.btnAddInternetBill)
        var ci_InternetBill :CircleImageView=findViewById(R.id.ci_InternetBill)
        var etInternetCost :EditText=findViewById(R.id.etInternetCost)
        var tvInternetDate:TextView=findViewById(R.id.tvInternetDate)


        ci_InternetBill.setOnClickListener {
            pickNewImage()
        }
        btnAddInternetBill.setOnClickListener {
            addingFuntion()

        }

        val cal = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            tvInternetDate.text = sdf.format(cal.time)
        }
        tvInternetDate.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun addingFuntion() {
        //var ci_InternetBill :CircleImageView=findViewById(R.id.ci_InternetBill)
        var etInternetCost :EditText=findViewById(R.id.etInternetCost)
        var tvInternetDate:TextView=findViewById(R.id.tvInternetDate)

        var billInto :String =(etInternetCost.text.toString()+"\n"+tvInternetDate.text.toString())
        var BillPhoto =imageUriTemp
        if (billInto!=null ){
            val resultIntent = Intent()
            resultIntent.putExtra("text", billInto)
            resultIntent.putExtra("image",BillPhoto.toString())
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
//        val returnIntent = Intent()
//        returnIntent.putExtra("billInfo", billInto)
//       // returnIntent.putExtra("billPhoto",BillPhoto)
//        setResult(Activity.RESULT_OK, returnIntent)
//        finish()}else{
  //          Toast.makeText(this,"something went wrong",Toast.LENGTH_SHORT).show()
        }

//        var intent:Intent=Intent(this,InternetRecycler::class.java).also {
//            it.putExtra("billInfo",billInto)
//            it.putExtra("billPhoto",BillPhoto)
//        }
//        startActivity(intent)
    }

    private fun pickNewImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var ciInternetBills=findViewById<CircleImageView>(R.id.ci_InternetBill)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data!!
            val imageStream = contentResolver.openInputStream(imageUri!!)
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            val id = System.currentTimeMillis()
            // val name = "something"
            ciInternetBills.setImageURI(imageUri)
            imageUriTemp=imageUri


          //  imageUriTemp=imageUri

        }
    }

}