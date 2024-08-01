package myRecycler

import android.app.AlertDialog
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semesterproject1.R

class myRecyclerActivity : AppCompatActivity() {
    private var usersAverage :Double =1.0
    lateinit var name: String
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter:FoodsAdapter
    private val PICK_IMAGE_REQUEST = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_recycler)
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(this)
        foodAdapter= FoodsAdapter(applicationContext,mutableListOf())
        recyclerView.adapter=foodAdapter
        foodAdapter.onItemClick= {
            val intent = Intent(this,DetailedRecycler::class.java)
            intent.putExtra("food",it)
            startActivity(intent)
        }
        var ibElectricityAdd = findViewById<ImageButton>(R.id.ibElectricityAdd)
        ibElectricityAdd.setOnClickListener{
            dialogFun()
        }
    }
    private fun dialogFun () {

        val view = View.inflate(this, R.layout.add_dialog, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        var btnCancel = view.findViewById<Button>(R.id.btnCancel)
        var btnAdd = view.findViewById<Button>(R.id.btnAdd)
        var etAddWaterBill = view.findViewById<EditText>(R.id.etAddWaterBill)
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        btnAdd.setOnClickListener {
            var etAddWaterBill = view.findViewById<EditText>(R.id.etAddWaterBill)
            var etAddDateWater = view.findViewById<EditText>(R.id.etAddDateWater)
            var tvAverageElectricity = findViewById<TextView>(R.id.tvAverageElectricity)
            var valu1: String =
                (etAddWaterBill.text.toString() + "\n" + etAddDateWater.text.toString())
            var edittoString = etAddWaterBill.text.toString()
            var editToInt =edittoString.toInt()
            usersAverage=(usersAverage*(foodAdapter.itemCount)+editToInt)/((foodAdapter.itemCount)+1)
            tvAverageElectricity.text = usersAverage.toString()
            name=valu1
            //here we use the intent to open the gallery
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
            etAddWaterBill.text.clear()
            etAddDateWater.text.clear()
            dialog.dismiss()
        }




    }


    //for adding a photo from the gallery
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data
            val imageStream = contentResolver.openInputStream(imageUri!!)
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            val id = System.currentTimeMillis()
            // val name = "something"
            val photo = Foods( name, imageUri)
            foodAdapter.addPhoto(photo)
            //foodAdapter.notifyItemInserted(foodAdapter.itemCount)
        }
    }
    //test test

    //function for and custom dialog to appear with the add button is pressed

}