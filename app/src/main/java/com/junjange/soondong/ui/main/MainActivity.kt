package com.junjange.soondong.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.junjange.soondong.R
import com.junjange.soondong.databinding.ActivityMainBinding
import com.junjange.soondong.ui.dialog.EditDialog
import com.junjange.soondong.ui.matching_detail.MatchingDetailActivity
import com.junjange.soondong.ui.matching.MatchingActivity
import com.junjange.soondong.ui.matching_edit.MatchingEditActivity
import com.junjange.soondong.ui.matching_today.MatchingTodayActivity
import com.junjange.soondong.ui.register.RegisterActivity
import com.junjange.soondong.ui.signin.SigninActivity
import com.junjange.soondong.utils.MyApplication

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel : MainViewModel by viewModels()
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
        binding.mainNavigationView.setNavigationItemSelectedListener(this) //navigation 리스너



        Log.d("ttt",MyApplication.prefs.getString("memberId", "").toString())
        // 축구 매칭
        binding.footballBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, MatchingActivity::class.java)
            intent.putExtra("sportsType", "SOCCER")
            startActivity(intent)

        }

        // 풋살 매칭
        binding.futsalBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, MatchingActivity::class.java)
            intent.putExtra("sportsType", "FUTSAL")
            startActivity(intent)
        }

        // 농구 매칭
        binding.basketballBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, MatchingActivity::class.java)
            intent.putExtra("sportsType", "BASKETBALL")
            startActivity(intent)
        }

        // 런닝 매칭
        binding.runningBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, MatchingActivity::class.java)
            intent.putExtra("sportsType", "RUNNING")
            startActivity(intent)
        }


        // 오늘의 경기
        binding.todayMatchBtn.setOnClickListener {
            startActivity( Intent(this@MainActivity, MatchingTodayActivity::class.java))

        }


        // 게시글 작성
        binding.newMatchBtn.setOnClickListener {

            val title = MyApplication.prefs.getString("title", "")
            val sports = MyApplication.prefs.getString("sports", "종목 선택")
            val place = MyApplication.prefs.getString("place", "")
            val recruit = MyApplication.prefs.getString("recruit", "")
            val gender = MyApplication.prefs.getString("gender", "모집 성별")
            val matchingDate = MyApplication.prefs.getString("matchingDate", "매칭 날짜")
            val matchingStartTime = MyApplication.prefs.getString("matchingStartTime", "매칭 시작시간")
            val matchingEndTime = MyApplication.prefs.getString("matchingEndTime", "매칭 종료시간")
            val content = MyApplication.prefs.getString("content", "")



            if (title != "" || sports != "종목 선택" || place != "" || recruit != "" ||  gender != "모집 성별" ||
                matchingDate != "매칭 날짜" || matchingStartTime != "매칭 시작시간" || matchingEndTime != "매칭 종료시간" || content != ""){

                startActivity(Intent(this, EditDialog::class.java))


            }else{
                startActivity(Intent(this, MatchingEditActivity::class.java))

            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{ // 메뉴 버튼
                binding.mainDrawerLayout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.mainDrawerLayout.closeDrawers()
        when(item.itemId){
//            R.id.mainPageDrawer-> {
//                startActivity( Intent(this@MainActivity, MainActivity::class.java))
//
//            }
            R.id.myPageDrawer-> {
//                startActivity( Intent(this@HomeActivity, PollsActivity::class.java))

            }
            R.id.todayMatchDrawer-> {
                startActivity( Intent(this@MainActivity, MatchingTodayActivity::class.java))

            }

            R.id.newMatchDrawer-> {
                startActivity( Intent(this@MainActivity, MatchingEditActivity::class.java))

            }
            R.id.searchDrawer-> {
//                startActivity( Intent(this@HomeActivity, SearchActivity::class.java))

            }

            R.id.logoutDrawer-> {


                // 로그아웃

                MyApplication.prefs.setString("title", "")
                MyApplication.prefs.setString("sports", "종목 선택")
                MyApplication.prefs.setString("place", "")
                MyApplication.prefs.setString("recruit", "")
                MyApplication.prefs.setString("gender", "모집 성별")
                MyApplication.prefs.setString("matchingDate", "매칭 날짜")
                MyApplication.prefs.setString("matchingStartTime", "매칭 시작시간")
                MyApplication.prefs.setString("matchingEndTime", "매칭 종료시간")
                MyApplication.prefs.setString("content", "")

                MyApplication.prefs.setString("memberId", "")
                startActivity( Intent(this@MainActivity, SigninActivity::class.java))
                finish()

            }

        }
        return false
    }


    // 뒤로가기 2번 눌러야 종료
    private val finishIntervalTime: Long = 2500
    private var backPressedTime: Long = 0
    private var toast: Toast? = null
    override fun onBackPressed() {

        // drawer 종료
        if(binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.mainDrawerLayout.closeDrawers()

            // 앱 종료
        } else{

            val tempTime = System.currentTimeMillis()
            val intervalTime = tempTime - backPressedTime

            // 뒤로 가기 할 경우 홈 화면으로 이동
            if (intervalTime in 0..finishIntervalTime) {
                super.onBackPressed()
                // 앱 종료시 뒤로가기 토스트 종료
                toast!!.cancel()
                finish()
            } else {
                backPressedTime = tempTime
                toast =
                    Toast.makeText(applicationContext, "'뒤로'버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT)
                toast!!.show()
            }
        }
    }
}