package com.junjange.soondong.ui.matching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.junjange.soondong.R
import com.junjange.soondong.adapter.CalendarAdapter
import com.junjange.soondong.data.CalendarDateModel
import com.junjange.soondong.databinding.ActivityMatchingBinding
import com.junjange.soondong.utils.HorizontalItemDecoration
import java.text.SimpleDateFormat
import java.util.*

class MatchingActivity : AppCompatActivity(), CalendarAdapter.ItemClickListener{

    private val binding by lazy { ActivityMatchingBinding.inflate(layoutInflater) }
    private val viewModel : MatchingViewModel by viewModels()
    private val sdf = SimpleDateFormat("yyyy년 MMMM", Locale.KOREA)
    private val cal = Calendar.getInstance(Locale.KOREA)
    private val currentDate = Calendar.getInstance(Locale.KOREA)
    private val dates = ArrayList<Date>()
    private lateinit var adapter: CalendarAdapter
    private val calendarDateList = ArrayList<CalendarDateModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setUpAdapter()
        setUpClickListener()
        setUpCalendar(currentDate.get(Calendar.DATE))
    }

    /**
     * 클릭 리스너 설정
     */
    private fun setUpClickListener() {
        binding.ivCalendarNext.setOnClickListener {
            cal.add(Calendar.MONTH, 1)
            if (cal == currentDate)
                setUpCalendar(currentDate.get(Calendar.DATE))
            else
                setUpCalendar(1)
        }
        binding.ivCalendarPrevious.setOnClickListener {
            cal.add(Calendar.MONTH, -1)

            if (cal == currentDate)
                setUpCalendar(currentDate.get(Calendar.DATE))
            else
                setUpCalendar(1)
        }
    }

    /**
     * 리사이클러뷰용 어댑터 설정
     */
    private fun setUpAdapter() {
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.single_calendar_margin)
        binding.recyclerView.addItemDecoration(HorizontalItemDecoration(spacingInPixels))
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)

        adapter =  CalendarAdapter(this).apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
        }

        binding.recyclerView.adapter = adapter
    }

    /**
     * 매월 달력을 설정하는 기능
     */
    private fun setUpCalendar(toDay : Int) {
        val calendarList = ArrayList<CalendarDateModel>()
        binding.tvDateMonth.text = sdf.format(cal.time)
        val monthCalendar = cal.clone() as Calendar
        val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        dates.clear()

        monthCalendar.set(Calendar.DAY_OF_MONTH, toDay)
        while (dates.size < maxDaysInMonth - toDay + 1) {
            dates.add(monthCalendar.time)
            calendarList.add(CalendarDateModel(monthCalendar.time))
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        calendarDateList.clear()
        calendarDateList.addAll(calendarList)
        calendarDateList[0].isSelected = true

        adapter.setData(calendarList)
    }

    // 클릭 리스너
    override fun onItemClickListener(item: CalendarDateModel, position: Int) {
        calendarDateList.forEachIndexed { index, calendarModel ->
            calendarModel.isSelected = index == position

        }
        adapter.setData(calendarDateList)

    }
}
