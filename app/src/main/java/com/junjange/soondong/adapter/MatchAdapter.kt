package com.junjange.soondong.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.junjange.soondong.R
import com.junjange.soondong.data.CalendarDateModel
import com.junjange.soondong.data.Match
import com.junjange.soondong.databinding.ItemRecyclerMatchBinding
import java.util.*
import kotlin.collections.ArrayList

class MatchAdapter (val onClickListener: ItemClickListener) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {
    private var items = ArrayList<Match>()
    private val gender = arrayListOf<String>("남녀모두", "남자만", "여자만")
    private val state = arrayListOf<String>("신청가능", "마감임박!", "마감")
    private val stateTextColor = arrayListOf<String>("#FFFFFF", "#FFFFFF", "#cccccc")
    private val stateBtnColor = arrayListOf<String>("#1570ff", "#ea3e42", "#EEEEEE")


    interface ItemClickListener {
        fun onItemClickListener(
            item: Match,
            position: Int,
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.bind(items[position])
    }



    inner class ViewHolder(private val binding: ItemRecyclerMatchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(match: Match) {
            binding.startTimeText.text = match.startTime
            binding.endTimeText.text = match.endTime
            binding.titleText.text = match.place
            binding.subTitleText.text = "${gender[match.gender]}•${match.people}•${match.level}"
            binding.stateBtn.setCardBackgroundColor(Color.parseColor(stateBtnColor[match.state]))
            binding.stateText.text = state[match.state]
            binding.stateText.setTextColor(Color.parseColor(stateTextColor[match.state]))


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
    internal fun setData(matchList: ArrayList<Match>) {

        this.items = matchList
        notifyDataSetChanged()

    }
}
