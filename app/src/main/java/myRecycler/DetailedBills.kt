package myRecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.semesterproject1.R

class DetailedBills : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_bills)
        val bill =intent.getParcelableExtra<Bills>("food")
        if (bill!= null){
            var tvBilldes : TextView = findViewById(R.id.tvBilldes)
            var ivDetailedbill : ImageView = findViewById(R.id.ivDetailedbill)
            var tvBillDetailedValue : TextView = findViewById(R.id.tvBillDetailedValue)
            var tvBillDetailedDate : TextView = findViewById(R.id.tvBillDetailedDate)
            tvBilldes.text ="Bill Description:\n"+bill.description
            Glide.with(applicationContext).load(bill.uri).into(ivDetailedbill)
            tvBillDetailedValue.text="Bill Value:\n"+bill.value.toString()
            tvBillDetailedDate.text=bill.date
        }
    }
}