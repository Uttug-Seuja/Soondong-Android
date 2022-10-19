package com.junjange.soondong.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.junjange.soondong.R
import com.junjange.soondong.data.CalendarDateModel
import com.junjange.soondong.data.Match
import com.junjange.soondong.data.ReservesSportDate
import com.junjange.soondong.data.ReservesSportDateData
import com.junjange.soondong.databinding.ItemRecyclerMatchBinding
import com.junjange.soondong.ui.matching_detail.MatchingDetailActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MatchAdapter (val onClickListener: ItemClickListener, val context: Context) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {
    private var items = ArrayList<ReservesSportDateData>()
//    private val gender = arrayListOf<String>("남녀모두", "남자만", "여자만")
    private val gender = hashMapOf<String, String>("ALL" to "남녀모두", "MAN" to "남자만", "WOMAN" to "여자만" )
    private val recruitmentNum = hashMapOf<String, String>("SOCCER" to "11vs11", "FUTSAL" to "6vs6", "RUNNING" to "최대인원", "BASKETBALL" to "8vs8"  )

    private val state = arrayListOf<String>("신청가능", "마감임박!", "마감")
    private val stateTextColor = arrayListOf<String>("#FFFFFF", "#FFFFFF", "#cccccc")
    private val stateBtnColor = arrayListOf<String>("#1570ff", "#FF4D37", "#EEEEEE")


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
        holder.clickItem(items[position])
    }



    inner class ViewHolder(private val binding: ItemRecyclerMatchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(reservesSportDateData: ReservesSportDateData) {

            binding.startTimeText.text = reservesSportDateData.startT.substring(0, 5)
            binding.endTimeText.text = reservesSportDateData.endT.substring(0, 5)
            binding.titleText.text = reservesSportDateData.title
            binding.subTitleText.text = "${gender[reservesSportDateData.gender]}•${recruitmentNum[reservesSportDateData.sport]}•모든레벨"
//            binding.stateBtn.setCardBackgroundColor(Color.parseColor(stateBtnColor[reservesSportDateData.state]))
//            binding.stateText.text = state[reservesSportDateData.state]
//            binding.stateText.setTextColor(Color.parseColor(stateTextColor[reservesSportDateData.state]))


        }

        fun clickItem(reservesSportDateData: ReservesSportDateData){
            binding.matchCardView.setOnClickListener {
                // 원하는 화면 연결
                Intent(context, MatchingDetailActivity::class.java).apply {
                    // 데이터 전달
                    putExtra("reserveId", reservesSportDateData.reserveId)

                }.run {
                    //액티비티 열기
                    context.startActivity(this)
                }
            }

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
    internal fun setData(reservesSportDateData: ArrayList<ReservesSportDateData>) {

        this.items = reservesSportDateData
        notifyDataSetChanged()

    }
}
