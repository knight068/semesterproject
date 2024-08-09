package myRecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.semesterproject1.R
class DetailedRecycler : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_recycler)
        val food =intent.getParcelableExtra<Foods>("food")
        if (food!= null){
            var textView : TextView = findViewById(R.id.tvBillsInfo)
            var imageView : ImageView = findViewById(R.id.ivDetailed)
            textView.text =food.name
//            Glide.with(applicationContext).load(food.uri).into(imageView)
            val myOptions = RequestOptions().override(350, 600)
            Glide.with(this)
                .asBitmap()
                .apply(myOptions)
                .load(food.uri)
                .into(imageView)
        }
    }
}