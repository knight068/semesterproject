package myRecycler

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.annotation.AnyRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semesterproject1.R

class AddingToRecyclerTest : AppCompatActivity() {

    lateinit var name: String
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter:FoodsAdapter
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var foodList :ArrayList<Foods>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_to_recycler_test)


        foodList=ArrayList()
        recyclerView=findViewById(R.id.testRecycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(this)
        foodAdapter= FoodsAdapter(applicationContext,mutableListOf())
        recyclerView.adapter=foodAdapter
        foodAdapter.onItemClick= {
            val intent = Intent(this,DetailedRecycler::class.java)
            intent.putExtra("food",it)
            startActivity(intent)

        }

        val resourceId = R.drawable.bill // Replace with your image resource ID
        val imageUri = getUriToDrawable(applicationContext, resourceId)
        val something = Foods("Iam a bitch",imageUri)
        foodAdapter.addPhoto(something)
        findViewById<View>(android.R.id.content).setOnClickListener{
            foodAdapter.notifyDataSetChanged()
        }
        var btnAdd :Button = findViewById(R.id.btnAdd1)
        btnAdd.setOnClickListener{
            dialogFun()
        }
    }
    fun getUriToDrawable(context: Context, @AnyRes drawableId: Int): Uri {
        val packageName = context.resources.getResourcePackageName(drawableId)
        val typeName = context.resources.getResourceTypeName(drawableId)
        val entryName = context.resources.getResourceEntryName(drawableId)
        return Uri.parse("android.resource://$packageName/$typeName/$entryName")
    }
    private fun dialogFun () {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_dialog,null)
        val addDialog = AlertDialog.Builder(this)
//        val btnAdd = v.findViewById<Button>(R.id.btnAdd)
//        val btnCancel=v.findViewById<Button>(R.id.btnCancel)
        addDialog.setView(v)
        addDialog.setPositiveButton("ok"){
            dialog,_->
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
            foodAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("cancel"){
            dialog,_-> dialog.dismiss()
        }
//        addDialog.create()
//        addDialog.show()

//        val view = View.inflate(this, R.layout.add_dialog, null)
//        val builder = AlertDialog.Builder(this)
//        builder.setView(view)
//        val dialog = builder.create()
//        dialog.show()
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        var btnCancel = findViewById<Button>(R.id.btnCancel)
//        var btnAdd = findViewById<Button>(R.id.btnAdd)
        //btnCancel.setOnClickListener {
           // setContentView(R.layout.activity_adding_to_recycler_test)
        //}
//        btnAdd.setOnClickListener {
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.type = "image/*"
//            startActivityForResult(intent, PICK_IMAGE_REQUEST)
//            foodAdapter.notifyDataSetChanged()
//          //  etAddWaterBill.text.clear()
//         //   etAddDateWater.text.clear()
//
//        }
        addDialog.create()
        addDialog.show()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data
            val imageStream = contentResolver.openInputStream(imageUri!!)
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            val id = System.currentTimeMillis()
            // val name = "something"
            val photo = Foods( "name", imageUri)
            foodAdapter.addPhoto(photo)
            foodAdapter.notifyDataSetChanged()
        }
    }

}