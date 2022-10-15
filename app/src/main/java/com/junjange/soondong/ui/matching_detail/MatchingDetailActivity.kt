package com.junjange.soondong.ui.matching_detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.junjange.soondong.R
import com.junjange.soondong.adapter.MatchAdapter
import com.junjange.soondong.adapter.MatchDataAdapter
import com.junjange.soondong.data.Participation
import com.junjange.soondong.databinding.ActivityMatchingDetailBinding
import com.junjange.soondong.ui.main.MainActivity
import com.junjange.soondong.ui.matching.MatchingViewModel
import com.junjange.soondong.utils.Constants

class MatchingDetailActivity : AppCompatActivity()  {
    private val binding by lazy { ActivityMatchingDetailBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this, MatchingDetailViewModel.Factory(application))[MatchingDetailViewModel::class.java] }

    private lateinit var matchDataAdapter: MatchDataAdapter

    private var bottomSheetDialog : BottomSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


//        setView()
        setMatchView()
        setObserver()

        /**
         * drawer
         *
         * */
        setSupportActionBar(binding.mainToolbar) // 툴바를 액티비티의 앱바로 지정
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_previous) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게



        binding.applyBtn.setOnClickListener {
//            viewModel.postParticipationRetrofit(Participation(1, 2))
//            viewModel.deleteParticipationRetrofit(Participation(1, 2))

        }

        /**
         * bottomSheetView
         *
         * */
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog!!.setContentView(bottomSheetView)
        bottomSheetView.findViewById<View>(R.id.mainToolbar).setOnClickListener {
            bottomSheetDialog!!.dismiss()
        }

        bottomSheetView.findViewById<View>(R.id.delete_btn).setOnClickListener {
//            viewModel.deleteReservesRetrofit(1)
            bottomSheetDialog!!.dismiss()
        }

        bottomSheetView.findViewById<View>(R.id.cancel_btn).setOnClickListener {
            bottomSheetDialog!!.dismiss()
        }


    }

    private fun setView(){
        viewModel.reservesInfoRetrofit(1)
        viewModel.retrofitReservesInfoText.observe(this){
            viewModel.retrofitReservesInfoText.value.let {

            }
        }
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
//        viewModel.participantUserInfoRetrofit(1)
//        viewModel.retrofitParticipantUserInfoText.observe(this){
//            viewModel.retrofitParticipantUserInfoText.value.let {
//                matchDataAdapter.setData(it.playerData)
//
//            }
//        }



    }

    //액션버튼 메뉴 액션바에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_ham_menu, menu)
        return true
    }

    //액션버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{ // 메뉴 버튼
                finish()
//                binding.mainDrawerLayout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
            }

            R.id.action_menu -> {
                bottomSheetDialog!!.show()


                return super.onOptionsItemSelected(item)
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