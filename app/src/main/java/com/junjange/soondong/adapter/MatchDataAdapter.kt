package com.junjange.soondong.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.junjange.soondong.data.CalendarDateModel
import com.junjange.soondong.data.Player
import com.junjange.soondong.data.PlayerData
import com.junjange.soondong.databinding.ItemRecyclerMatchDataBinding

class MatchDataAdapter () : RecyclerView.Adapter<MatchDataAdapter.ViewHolder>() {
    private var items = ArrayList<PlayerData>()




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerMatchDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.bind(items[position])
    }



    inner class ViewHolder(private val binding: ItemRecyclerMatchDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(playerData: PlayerData) {
            binding.studentIdText.text = playerData.schoolNum
            binding.nameText.text = playerData.name

        }

    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    fun setItem(item: CalendarDateModel){

    }


    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun getItemCount() : Int = items.size


    @SuppressLint("NotifyDataSetChanged")
    internal fun setData(player: ArrayList<PlayerData>) {

        this.items = player
        notifyDataSetChanged()

    }
}
