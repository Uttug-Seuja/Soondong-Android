package com.junjange.soondong.ui.matching_detail

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import com.junjange.soondong.ui.matching.MatchingActivity
import com.junjange.soondong.ui.matching.MatchingViewModel
import com.junjange.soondong.ui.matching_update.MatchingUpdateActivity
import com.junjange.soondong.utils.Constants
import com.junjange.soondong.utils.MyApplication
import java.text.SimpleDateFormat
import java.util.*

class MatchingDetailActivity : AppCompatActivity()  {
    private val binding by lazy { ActivityMatchingDetailBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this, MatchingDetailViewModel.Factory(application))[MatchingDetailViewModel::class.java] }

    private lateinit var matchDataAdapter: MatchDataAdapter

    private var bottomSheetDialog : BottomSheetDialog? = null
    private var reserveId : String? = null
    private var reserveUserId : Int? = null
    private var userId : String? = null
    private var sportType : String? = null
    private var recruit : String? = null
    private var genderText : String? = null


    private val gender = hashMapOf<String, String>("ALL" to "남녀모두", "MAN" to "남자만", "WOMAN" to "여자만" )
    private val ruleMember = hashMapOf<String, String>("SOCCER" to "11vs11", "FUTSAL" to "6vs6", "RUNNING" to "최대인원", "BASKETBALL" to "8vs8")
    private val shoes = hashMapOf<String, String>("SOCCER" to "풋살화/운동화", "FUTSAL" to "풋살화/운동화", "RUNNING" to "런닝화/운동화", "BASKETBALL" to "농구화/운동화")
    private val reserveStatus = hashMapOf<String, String>("POSSIBLE" to "신청하기" , "IMMINENT" to "신청하기",  "DEADLINE" to "신청 마감" )
    private val stateTextColor = hashMapOf<String, String>("POSSIBLE" to "#FFFFFF" , "IMMINENT" to "#FFFFFF",  "DEADLINE" to "#cccccc" )
    private val stateBtnColor = hashMapOf<String, String>("POSSIBLE" to "#1570ff" , "IMMINENT" to "#1570ff",  "DEADLINE" to "#EEEEEE" )
    private val sdf = SimpleDateFormat("MMMM d일 EEEE", Locale.KOREA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        reserveId = intent.getIntExtra("reserveId", 0).toString()
        reserveUserId = intent.getIntExtra("userId", 0)

        userId = MyApplication.prefs.getString("memberId", "")
        setView()
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

            if (binding.applyText.text == "신청하기"){
                viewModel.postParticipationRetrofit(Participation(userId!!.toInt(), reserveId!!.toInt()))


            }else{
                viewModel.deleteParticipationRetrofit(Participation(userId!!.toInt(), reserveId!!.toInt()))

            }

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
            viewModel.deleteReservesRetrofit(reserveId!!.toInt())

            viewModel.retrofitDeleteReservesText.observe(this){
                viewModel.retrofitDeleteReservesText.value.let {
                    if (it == true){
                        bottomSheetDialog!!.dismiss()
                        val intent = Intent(this@MatchingDetailActivity, MatchingActivity::class.java)
                        intent.apply {
                            this.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        }
                        intent.putExtra("sportsType", sportType)
                        startActivity(intent)
                        finish()
                    }

                }
            }


        }


        bottomSheetView.findViewById<View>(R.id.mainToolbar).setOnClickListener {
            bottomSheetDialog!!.dismiss()

            val intent = Intent(this@MatchingDetailActivity, MatchingUpdateActivity::class.java)
            intent.putExtra("reserveId", reserveId!!.toInt())
            intent.putExtra("userId", userId)
            intent.putExtra("recruit", recruit)
            intent.putExtra("gender", binding.matchGenderText.text)
            intent.putExtra("sports", sportType)


            startActivity(intent)
        }


        bottomSheetView.findViewById<View>(R.id.cancel_btn).setOnClickListener {
            bottomSheetDialog!!.dismiss()
        }



    }

    @SuppressLint("SetTextI18n")
    private fun setView(){
        viewModel.reservesInfoRetrofit(reserveId!!.toInt())
        viewModel.retrofitReservesInfoText.observe(this){
            viewModel.retrofitReservesInfoText.value.let {
//                android:text="10월 5일 수요일 10:00 ~ 12:00"

                val dateFormat = SimpleDateFormat("yyyy-MM-dd");

                val  parseDate = dateFormat.parse(it!!.reservesInfoData.reserveDate )
                sportType = it.reservesInfoData.sport
                binding.matchDayText.text = sdf.format(parseDate) + " " + it.reservesInfoData.startT.substring(0, 5) + " ~ " + it.reservesInfoData.endT.substring(0, 5)
                binding.matchPlaceText.text = it!!.reservesInfoData.place
                binding.matchReserveUserNameText.text = it.reservesInfoData.name
                binding.matchReserveUserStudentIdText.text = it.reservesInfoData.schoolNum
                binding.matchTitleText.text = it.reservesInfoData.title
                binding.matchExplanationText.text = it.reservesInfoData.explanation
                binding.matchGenderText.text = gender[it.reservesInfoData.gender]
//                binding.matchRuleText.text = ruleMember[it.reservesInfoData.sport]
                binding.matchRecruitmentNumText.text = "현재 ${it.reservesInfoData.currentNum}/${it.reservesInfoData.recruitmentNum}명"
                recruit = it.reservesInfoData.recruitmentNum.toString()
                binding.matchShoesText.text = shoes[it.reservesInfoData.sport]
                binding.applyBtn.setCardBackgroundColor(Color.parseColor(stateBtnColor[it.reservesInfoData.reserveStatus]))
                binding.applyText.text = reserveStatus[it.reservesInfoData.reserveStatus]
                binding.applyText.setTextColor(Color.parseColor(stateTextColor[it.reservesInfoData.reserveStatus]))


                if (it.reservesInfoData.reserveStatus == "DEADLINE"){
                    binding.applyBtn.isClickable = false
                    binding.applyExplanationText.text = "다음 일정을 미리 예약하세요"

                }else if (it.reservesInfoData.reserveStatus == "IMMINENT"){
                    binding.applyExplanationText.text = "진행 확정까지\n${it.reservesInfoData.recruitmentNum - it.reservesInfoData.currentNum}자리 남았어요"


                }

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

//        val player = Constants.getPlayer()
//        matchDataAdapter.setData(player)
        viewModel.participantUserInfoRetrofit(reserveId!!.toInt())
        viewModel.retrofitParticipantUserInfoText.observe(this){
            viewModel.retrofitParticipantUserInfoText.value.let {
                matchDataAdapter.setData(it!!.playerData)

               if(it.playerData.find {
                       it.userId == userId!!.toInt()
                   } != null){

                   binding.applyText.text = "취소하기"
                   binding.applyBtn.setCardBackgroundColor(Color.parseColor("#FF4D37"))


               }
            }
        }



    }

    //액션버튼 메뉴 액션바에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("ttt11111111", userId.toString())
        Log.d("ttt111111112", reserveUserId.toString())

        if (userId!!.toInt() == reserveUserId){

            menuInflater.inflate(R.menu.toolbar_ham_menu, menu)
        }
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

}