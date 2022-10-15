package com.junjange.soondong.ui.matching_today

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
import com.junjange.soondong.adapter.MatchTodayAdapter
import com.junjange.soondong.data.CalendarDateModel
import com.junjange.soondong.data.Match
import com.junjange.soondong.databinding.ActivityMatchingBinding
import com.junjange.soondong.databinding.ActivityMatchingTodayBinding
import com.junjange.soondong.ui.matching.MatchingViewModel
import com.junjange.soondong.ui.matching_edit.MatchingEditViewModel
import com.junjange.soondong.utils.Constants
import com.junjange.soondong.utils.HorizontalItemDecoration
import java.text.SimpleDateFormat
import java.util.*

class MatchingTodayActivity : AppCompatActivity() , MatchTodayAdapter.ItemClickListener{

    private val binding by lazy { ActivityMatchingTodayBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this, MatchingTodayViewModel.Factory(application))[MatchingTodayViewModel::class.java] }

    private val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    private val cal = Calendar.getInstance(Locale.KOREA)
    private val currentDate = Calendar.getInstance(Locale.KOREA)
    private val dates = ArrayList<Date>()
    private lateinit var calendarAdapter: CalendarAdapter
    private lateinit var matchTodayAdapter: MatchTodayAdapter

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


        setMatchView()
        setObserver()

    }

    private fun setMatchView(){
        matchTodayAdapter =  MatchTodayAdapter(this, this).apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
        }
        binding.rvMatch.adapter = matchTodayAdapter
    }

    private fun setObserver() {

        val matchList = Constants.getMatches()

        matchTodayAdapter.setData(matchList)

//        viewModel.reservesSportTodayRetrofit(sdf.format(cal.time).toString())

//        viewModel.retrofitReservesSportTodayText.observe(this){
//            viewModel.retrofitReservesSportTodayText.value.let {
//                matchTodayAdapter.setData(it)
//
//
//            }
//
//        }


    }

    /**
     * 클릭 리스너 설정
     */


    /**
     * 리사이클러뷰용 어댑터 설정
     */


    /**
     * 매월 달력을 설정하는 기능
     */



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
