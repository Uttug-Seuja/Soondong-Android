package com.junjange.soondong.ui.matching_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.junjange.soondong.R
import com.junjange.soondong.adapter.MatchAdapter
import com.junjange.soondong.adapter.MatchDataAdapter
import com.junjange.soondong.databinding.ActivityMatchingDetailBinding
import com.junjange.soondong.utils.Constants

class MatchingDetailActivity : AppCompatActivity()  {
    private val binding by lazy { ActivityMatchingDetailBinding.inflate(layoutInflater) }
    private val viewModel : MatchingDetailViewModel by viewModels()
    private lateinit var matchDataAdapter: MatchDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        /**
         * drawer
         *
         * */
        setSupportActionBar(binding.mainToolbar) // 툴바를 액티비티의 앱바로 지정
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게


//        binding.mainNavigationView.setNavigationItemSelectedListener(this) //navigation 리스너

        setMatchView()
        setObserver()
    }

    private fun setMatchView(){
        matchDataAdapter =  MatchDataAdapter().apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
        }
        binding.rvMatchData.adapter = matchDataAdapter
    }

    private fun setObserver() {
        val player = Constants.getPlayer()
        matchDataAdapter.setData(player)



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{ // 메뉴 버튼
//                binding.mainDrawerLayout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
            }
        }
        return super.onOptionsItemSelected(item)
    }
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        binding.mainDrawerLayout.closeDrawers()
//        when(item.itemId){
////            R.id.mainPageDrawer-> {
////                startActivity( Intent(this@MainActivity, MainActivity::class.java))
////
////            }
//            R.id.allPollsDrawer-> {
////                startActivity( Intent(this@HomeActivity, PollsActivity::class.java))
//
//            }
//            R.id.hotPollsDrawer-> {
////                startActivity( Intent(this@HomeActivity, HotPollsActivity::class.java))
//
//            }
//            R.id.searchDrawer-> {
////                startActivity( Intent(this@HomeActivity, SearchActivity::class.java))
//
//            }
//            R.id.myPageDrawer-> {
//                // My Page 이동
////                startActivity(Intent(this@HomeActivity, MyPageActivity::class.java))
//
//            }
//
//            R.id.newPollDrawer-> {
//                // My Page 이동
////                startActivity(Intent(this@HomeActivity, NewPollActivity::class.java))
//
//            }
//
//            R.id.logoutDrawer-> {
//                // 로그아웃
//
//            }
//
//        }
//        return false
//    }
}