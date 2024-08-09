package myRecycler

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semesterproject1.Budget
import com.example.semesterproject1.MainScreen
import com.example.semesterproject1.ProfileEdit
import com.example.semesterproject1.R
import com.example.semesterproject1.Reminders

class TipsAndTricks : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var tipsAdapter:TipsAndTrickAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips_and_tricks)
        bindBottomNavigation()


        recyclerView=findViewById(R.id.rvTips)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= GridLayoutManager(applicationContext,3,LinearLayoutManager.VERTICAL,false)
        tipsAdapter= TipsAndTrickAdapter(applicationContext,mutableListOf())
        recyclerView.adapter=tipsAdapter
        tipsAdapter.onItemClick= {
            val intent = Intent(this,DetailedRecycler::class.java)
            intent.putExtra("food",it)
            startActivity(intent)
        }
        var uri = Uri.parse("https://drive.google.com/thumbnail?id=12lSKGjdpprxskmCiJY5h5FAii1PFiJ7_")
        var photo = Foods( "taking debt is normal", uri)
        tipsAdapter.addPhoto(photo)
        uri =Uri.parse("https://drive.google.com/thumbnail?id=138BjLx1zoOzEMntUmV9i7Jyhx8Ye1XRk")
        photo = Foods( "7 Smart Money Habits", uri)
        tipsAdapter.addPhoto(photo)
        uri =Uri.parse("https://drive.google.com/thumbnail?id=12xrzHyv0dt_m1S-k_s8y7iHJVBnLg8FO")
        photo = Foods( "7 Sign of Bad Financial Health", uri)
        tipsAdapter.addPhoto(photo)
        uri =Uri.parse("https://drive.google.com/thumbnail?id=12uhJvHVMqI9t8JQSr9srZUiGsFo4Au0S")
        photo = Foods( "Smart Habits To Achieve Debt Free Life", uri)
        tipsAdapter.addPhoto(photo)
        uri =Uri.parse("https://drive.google.com/thumbnail?id=12m_fGpr-R7CZy3zj5RfFHJLtNsIXFScK")
        photo = Foods( "Does And Don'ts Of Debt Management", uri)
        tipsAdapter.addPhoto(photo)
        uri =Uri.parse("https://drive.google.com/thumbnail?id=12hJ8wi7JglKhEOT8ekqUXuV0SscQ_BY4")
        photo = Foods( "Does And Don'ts Of Debt Management", uri)
        tipsAdapter.addPhoto(photo)


    }
    private fun bindBottomNavigation() {
        var ibBudget : ImageButton =findViewById(R.id.ibBudget)
        ibBudget.setOnClickListener{
            val intent:Intent=Intent(this, Budget::class.java)
            startActivity(intent)
        }
        var ibProfile : ImageButton =findViewById(R.id.ibProfile)
        ibProfile.setOnClickListener{
            val intent:Intent=Intent(this, ProfileEdit::class.java)
            startActivity(intent)
        }
        var ibBills : ImageButton =findViewById(R.id.ibBills)
        ibBills.setOnClickListener{
            val intent:Intent=Intent(this,MainScreen::class.java)
            startActivity(intent)
        }
        var ibReminders : ImageButton =findViewById(R.id.ibReminders)
        ibReminders.setOnClickListener{
            val intent:Intent=Intent(this, Reminders::class.java)
            startActivity(intent)
        }

    }
}