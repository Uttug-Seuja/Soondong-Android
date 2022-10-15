package com.junjange.soondong.ui.matching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.junjange.soondong.R
import com.junjange.soondong.adapter.CalendarAdapter
import com.junjange.soondong.adapter.MatchAdapter
import com.junjange.soondong.data.CalendarDateModel
import com.junjange.soondong.data.Match
import com.junjange.soondong.databinding.ActivityMatchingBinding
import com.junjange.soondong.utils.Constants
import com.junjange.soondong.utils.HorizontalItemDecoration
import java.text.SimpleDateFormat
import java.util.*

class MatchingActivity : AppCompatActivity(), CalendarAdapter.ItemClickListener, MatchAdapter.ItemClickListener{

    private val binding by lazy { ActivityMatchingBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this, MatchingViewModel.Factory(application))[MatchingViewModel::class.java] }
    private val sdf = SimpleDateFormat("yyyy년 MMMM", Locale.KOREA)
    private val cal = Calendar.getInstance(Locale.KOREA)
    private val currentDate = Calendar.getInstance(Locale.KOREA)
    private val dates = ArrayList<Date>()
    private lateinit var calendarAdapter: CalendarAdapter
    private lateinit var matchAdapter: MatchAdapter

    private val calendarDateList = ArrayList<CalendarDateModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setSupportActionBar(binding.mainToolbar) // 툴바를 액티비티의 앱바로 지정
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게

        setUpDateAdapter()
        setUpDateClickListener()
        setUpCalendar(currentDate.get(Calendar.DATE) - 1)
        binding.recyclerView.scrollToPosition(currentDate.get(Calendar.DATE) - 1)


        setMatchView()
        setObserver()

    }

    private fun setMatchView(){
        matchAdapter =  MatchAdapter(this,this).apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
        }
        binding.rvMatch.adapter = matchAdapter
    }

    private fun setObserver() {

        val matchList = Constants.getMatches()

        matchAdapter.setData(matchList)

//        viewModel.retrofitReservesInfoRetrofit("SOCCER", sdf.format(cal.time).toString())
//
//        viewModel.reservesSportDateText.observe(this){
//            viewModel.reservesSportDateText.value.let {
//                matchAdapter.setData(it)
//
//            }
//        }



    }

    /**
     * 클릭 리스너 설정
     */
    private fun setUpDateClickListener() {
        binding.ivCalendarNext.setOnClickListener {
            cal.add(Calendar.MONTH, 1)
            if (cal == currentDate) {
                setUpCalendar(currentDate.get(Calendar.DATE) -1)
                binding.recyclerView.scrollToPosition(currentDate.get(Calendar.DATE) - 1 + 4)
                Log.d("ttt", currentDate.get(Calendar.DATE).toString())

            }else{
                setUpCalendar(0)
                binding.recyclerView.scrollToPosition(0)

            }
        }
        binding.ivCalendarPrevious.setOnClickListener {
            cal.add(Calendar.MONTH, -1)

            if (cal == currentDate) {
                setUpCalendar(currentDate.get(Calendar.DATE) -1 )
                binding.recyclerView.scrollToPosition(currentDate.get(Calendar.DATE) - 1 + 4)

            }else{
                setUpCalendar(0)
                binding.recyclerView.scrollToPosition(0)

            }
        }
    }

    /**
     * 리사이클러뷰용 어댑터 설정
     */
    private fun setUpDateAdapter() {
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.single_calendar_margin)
        binding.recyclerView.addItemDecoration(HorizontalItemDecoration(spacingInPixels))
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)

        calendarAdapter =  CalendarAdapter(this).apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
        }

        binding.recyclerView.adapter = calendarAdapter
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

        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)
        while (dates.size < maxDaysInMonth) {
            dates.add(monthCalendar.time)
            calendarList.add(CalendarDateModel(monthCalendar.time))
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        calendarDateList.clear()
        calendarDateList.addAll(calendarList)
        calendarDateList[toDay].isSelected = true

        calendarAdapter.setData(calendarList)
    }

    // 클릭 리스너
    override fun onItemClickListener(item: CalendarDateModel, position: Int) {
        calendarDateList.forEachIndexed { index, calendarModel ->
//            calendarModel.isSelected = index == position
            if(index == position){
                calendarModel.isSelected = true
//                viewModel.retrofitReservesInfoRetrofit("SOCCER", sdf.format(cal.time).toString())
                // API 호출
            }else{
                calendarModel.isSelected = false
            }

        }
        calendarAdapter.setData(calendarDateList)

    }

    override fun onItemClickListener(item: Match, position: Int) {
        TODO("Not yet implemented")
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{ // 메뉴 버튼
                finish()
//                binding.mainDrawerLayout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
