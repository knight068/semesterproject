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
        var uri = Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRyq8Bj1EsTqhcfMn01ypklCsF7-uRqVxMAfw&s")
        var photo = Foods( "billPhotoExtra", uri)
        tipsAdapter.addPhoto(photo)
        tipsAdapter.addPhoto(photo)
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