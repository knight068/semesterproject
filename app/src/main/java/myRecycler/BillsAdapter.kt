package myRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.semesterproject1.R

class BillsAdapter(val con: Context, val billsList: MutableList<Bills>): RecyclerView.Adapter<BillsAdapter.BillsViwHolder>() {
    var onItemClick :((Bills) -> Unit)?= null
    class BillsViwHolder (itemView: View) : RecyclerView.ViewHolder (itemView){
        val billsImage : ImageView =itemView.findViewById(R.id.ciRcBills)
        val billsDescription : TextView =itemView.findViewById(R.id.tvBillDescription)
        val tvBillValue : TextView =itemView.findViewById(R.id.tvBillValue)
        val tvBillDate : TextView =itemView.findViewById(R.id.tvBillDate)
        val imageButton : ImageButton =itemView.findViewById(R.id.btnDeleteRecycler)
//        fun  bind (photo: Foods){
////            rcImageView.setImageURI(photo.uri)
//            Glide.with()
//            textView.text=photo.name
//        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillsViwHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bill_item,parent,false)
        return BillsViwHolder(view)

    }

    override fun getItemCount(): Int {
        return billsList.size
    }

    override fun onBindViewHolder(holder: BillsViwHolder, position: Int) {
        val bill =billsList[position]
//        holder.bind(food)
        Glide.with(con).load(bill.uri).into(holder.billsImage)
        holder.billsDescription.text=bill.description
        holder.tvBillValue.text= bill.value.toString()
        holder.tvBillDate.text=bill.date

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(bill)

        }
        holder.imageButton.setOnClickListener {
            billsList.removeAt(position)
            notifyItemRemoved(position)



        }

    }
    fun addPhoto(bill:Bills){
        billsList.add(bill)
        notifyItemInserted(billsList.size-1)
        notifyDataSetChanged()
    }

}