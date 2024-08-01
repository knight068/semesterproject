package myRecycler

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semesterproject1.R
import com.example.semesterproject1.api.RetrofitClient
import com.example.semesterproject1.models.NetBillsResponse
import com.example.semesterproject1.storage.SharedPrefManager
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class InternetRecycler : AppCompatActivity() {
//    private val secondActivityLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                val data: Intent? = result.data
//                var billInfo = data?.getStringExtra("text")
//                var billImageUri = data?.getStringExtra("image")
//                var tempUri = Uri.parse(billImageUri)
//                val photo = Foods( billInfo!!, tempUri)
//                foodAdapter.addPhoto(photo)
//                Toast.makeText(this,billInfo+billImageUri,Toast.LENGTH_SHORT).show()
////                val photo = Foods( billInfo!!, tempUri)
////                foodAdapter.addPhoto(photo)
////                foodAdapter.notifyDataSetChanged()
//
//
//
//            }
//        }
    private  lateinit var tempUri: Uri
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter:BillsAdapter
    private val PICK_IMAGE_REQUEST = 1
    private val RETURN_BILL_INFO = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet_recycler)


        var userid=SharedPrefManager.getInstance(applicationContext).user!!._id.toString()

        RetrofitClient.instance.getBills(userid).enqueue(object : Callback<NetBillsResponse>{
            override fun onResponse(call: Call<NetBillsResponse>, response: Response<NetBillsResponse>) {
                var temp=response.body().toString()
                Log.i("temp body",temp)
            }

            override fun onFailure(call: Call<NetBillsResponse>, t: Throwable) {
                Toast.makeText(applicationContext,t.message.toString(),Toast.LENGTH_SHORT).show()
            }
        })
        findViewById<View>(android.R.id.content).setOnClickListener{
            foodAdapter.notifyDataSetChanged()
        }



//        if(billInfoFromExtra!=null&&billPhotoExtra!=null){
//            val photo = Foods( billPhotoExtra!!, tempUri)
//            foodAdapter.addPhoto(photo)
//        }


        var ibInternetAdd = findViewById<ImageButton>(R.id.ibInternetAdd)


        recyclerView = findViewById(R.id.internetRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        foodAdapter = BillsAdapter(applicationContext, mutableListOf())
        recyclerView.adapter = foodAdapter
        foodAdapter.onItemClick = {
            val intent = Intent(this, DetailedBills::class.java)
            intent.putExtra("food", it)
            startActivity(intent)
        }
        val image=Uri.parse("data:image/jpeg;base64,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")
        var tempBill=Bills("someDetails",5000.0,"2022",image)
        foodAdapter.addPhoto(tempBill)



        ibInternetAdd.setOnClickListener {
//            val intent = Intent(this, AddingToInternetBills::class.java)
//            secondActivityLauncher.launch(intent)
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
    }

    private fun addingFuntion() {
        var btnAddInternetBill :Button=findViewById(R.id.btnAddInternetBill)
        var ci_InternetBill :CircleImageView=findViewById(R.id.ci_InternetBill)
        var etInternetCost :EditText=findViewById(R.id.etInternetCost)
        var tvInternetDate:TextView=findViewById(R.id.tvInternetDate)

        var tempText = (etInternetCost.text.toString()+"\n"+tvInternetDate.text.toString())
        var newItem =Foods(tempText,tempUri)
//        foodAdapter.addPhoto(newItem)
        foodAdapter.notifyDataSetChanged()
        resetContentView()


    }

    private fun resetContentView() {
        setContentView(R.layout.activity_internet_recycler)
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
            tempUri=imageUri



            //  imageUriTemp=imageUri

        }
    }
}

