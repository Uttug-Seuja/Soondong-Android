package com.junjange.soondong.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.junjange.soondong.data.CalendarDateModel
import com.junjange.soondong.data.Player
import com.junjange.soondong.databinding.ItemRecyclerMatchDataBinding

class MatchDataAdapter () : RecyclerView.Adapter<MatchDataAdapter.ViewHolder>() {
    private var items = ArrayList<Player>()




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerMatchDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.bind(items[position])
    }



    inner class ViewHolder(private val binding: ItemRecyclerMatchDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {
            binding.studentIdText.text = player.studentId
            binding.nameText.text = player.name

        }

    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    fun setItem(item: CalendarDateModel){

    }


    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun getItemCount() : Int = 20


    @SuppressLint("NotifyDataSetChanged")
    internal fun setData(player: ArrayList<Player>) {

        this.items = player
        notifyDataSetChanged()

    }
}
