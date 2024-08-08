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
import kotlinx.coroutines.delay
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
                var adapterList= mutableListOf<Bills>()
                var tempUriForAdapter=Uri.parse("https://jpeg-optimizer.com/image/ads1.webp")

                var tempList = response.body()!!.data4.NetData
                for ( i in tempList){
                    var tempBill :Bills= Bills("somthing", 500F,"some",tempUriForAdapter)
                    tempBill.uri=Uri.parse(i.photo)
                    tempBill.description=i.name
                    tempBill.date=i.date
                    tempBill.value=i.value.toFloat()
                    adapterList.add(tempBill)
                    Log.i("data5",tempBill.toString())
                    foodAdapter.addPhoto(tempBill)
                    foodAdapter.notifyDataSetChanged()

                }
                for (l in adapterList){
                    Log.i("data6",l.toString())
//                    foodAdapter.addPhoto(l)
//                    foodAdapter.notifyDataSetChanged()
                }

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
        foodAdapter= BillsAdapter(applicationContext, mutableListOf())

        recyclerView.adapter = foodAdapter
        foodAdapter.onItemClick = {
            val intent = Intent(this, DetailedBills::class.java)
            intent.putExtra("food", it)
            startActivity(intent)
        }
        val image=Uri.parse("https://drive.google.com/thumbnail?id=1By-TiWOwAE38bvVPjcZ4uYzq83ZDgrDz")
        var tempBill=Bills("someDetails",5000f,"2022",image)
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

