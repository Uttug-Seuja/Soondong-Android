package com.junjange.soondong.ui.matching_update

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.junjange.soondong.R
import com.junjange.soondong.data.ReservesEdit
import com.junjange.soondong.databinding.ActivityMatchingUpdateBinding
import com.junjange.soondong.ui.matching_detail.MatchingDetailActivity
import com.junjange.soondong.utils.MyApplication
import java.util.*

class MatchingUpdateActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMatchingUpdateBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this, MatchingUpdateViewModel.Factory(application))[MatchingUpdateViewModel::class.java] }

    private var title : String? = null
    private var sports : String? = null
    private var place : String? = null
    private var recruit : String? = null
    private var gender : String? = null
    private var matchingDate : String? = null
    private var matchingStartTime : String? = null
    private var matchingEndTime : String? = null
    private var content : String? = null
    private var member : Int? = null
    private val genderMap = hashMapOf<String, String>("남녀 모두" to "ALL", "남자만" to "MAN", "여자만" to "WOMAN" )
    private val genderReadMap = hashMapOf<String, String>("ALL" to "남녀 모두", "MAN" to "남잠나", "WOMAN" to "여자만" )
    private val sportsMap = hashMapOf<String, String>("축구" to "SOCCER", "풋살" to "FUTSAL", "런닝" to "RUNNING", "농구" to "BASKETBALL" )
    private val sportsReadMap = hashMapOf<String, String>("SOCCER" to "축구", "FUTSAL" to "풋살", "RUNNING" to "런닝", "BASKETBALL" to "농구" )

    var historyTitle: String? = null
    var historyMood: String? = null
    var historyComment: String? = null
    var historyPlaceTitle: String? = null
    var historyUserID: Int? = null
    var historyCreatedAt: String? = null  // historyDate + historyTime
    var historyDate: String? = null
    var historyTime: String? = null
    private var reserveId : Int? = null
    private var reserveUserId : Int? = null
    private var userId : String? = null
    private var sportType : String? = null


    // Date, Time Picker 기본 값으로 사용될 현재 날짜 및 시각 가져옴
    private val calendarInstance = Calendar.getInstance()
    private val year = calendarInstance.get(Calendar.YEAR)
    private val month = calendarInstance.get(Calendar.MONTH)
    private val day = calendarInstance.get(Calendar.DAY_OF_MONTH)
    private val hour = calendarInstance.get(Calendar.HOUR_OF_DAY)
    private val minute = calendarInstance.get(Calendar.MINUTE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        reserveId = intent.getIntExtra("reserveId", 0)
        reserveUserId = intent.getIntExtra("userId", 0)

        setSupportActionBar(binding.mainToolbar) // 툴바를 액티비티의 앱바로 지정
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게

        viewModel.reservesInfoRetrofit(reserveId!!)


        viewModel.retrofitReservesInfoText.observe(this){
            viewModel.retrofitReservesInfoText.value.let {
                title = it!!.reservesInfoData.title
                sports = sportsReadMap[it!!.reservesInfoData.sport]
                place = it.reservesInfoData.place
                recruit = it.reservesInfoData.recruitmentNum.toString()
                gender = genderReadMap[it.reservesInfoData.gender]
                matchingDate = it.reservesInfoData.reserveDate
                matchingStartTime = it.reservesInfoData.startT.replaceFirst(":", "시 ").replaceFirst(":00", "분")
                matchingEndTime = it.reservesInfoData.endT.replaceFirst(":", "시 ").replaceFirst(":00", "분")
                content = it.reservesInfoData.explanation

                binding.editHistoryTitle.setText(it!!.reservesInfoData.title)
                binding.sportsText.text = sportsReadMap[it!!.reservesInfoData.sport]
                binding.editHistoryPlace.setText(it.reservesInfoData.place)
                binding.editHistoryRecruit.setText(it.reservesInfoData.recruitmentNum.toString())
                binding.genderText.text = genderReadMap[it.reservesInfoData.gender]
                binding.editHistoryDate.text = it.reservesInfoData.reserveDate
                binding.editHistoryStartTime.text = it.reservesInfoData.startT.replaceFirst(":", "시 ").replaceFirst(":00", "분")
                binding.editHistoryEndTime.text = it.reservesInfoData.endT.replaceFirst(":", "시 ").replaceFirst(":00", "분")
                binding.editHistoryContent.setText(it.reservesInfoData.explanation)

            }

        }
        addTextChangedListener()
        setOnClickListener()

    }



    //액션버튼 메뉴 액션바에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    //액션버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){

            android.R.id.home -> {
                finish()
                return super.onOptionsItemSelected(item)
            }


            R.id.action_search -> {
                matchCrateClickListener()

                return super.onOptionsItemSelected(item)
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun addTextChangedListener(){

        binding.editHistoryTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {

                title = binding.editHistoryTitle.text.toString()


            }
        })


        binding.editHistoryPlace.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {

                place = binding.editHistoryPlace.text.toString()


            }
        })

        binding.editHistoryRecruit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {

                recruit = binding.editHistoryRecruit.text.toString()


            }
        })

        binding.editHistoryContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {

                content = binding.editHistoryContent.text.toString()

            }
        })


    }




    private fun setOnClickListener(){


        // 히스토리 날짜 입력
        binding.editHistoryDateBtn.setOnClickListener {
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    historyDate =
                        year.toString() + "-"
                    //DatePicker 특성 상 한 자리 날짜 입력에 대한 대응을 해줘야 함
                    historyDate +=
                        if ((monthOfYear + 1) < 10) "0" + (monthOfYear + 1).toString() + "-"
                        else (monthOfYear + 1).toString() + "-"
                    historyDate +=
                        if (dayOfMonth < 10) "0$dayOfMonth"
                        else "$dayOfMonth"

                    binding.editHistoryDate.text = historyDate
                    matchingDate = historyDate


                }, year, month, day
            )
            datePicker.show()
        }

        // 히스토리 시작 시간 입력
        binding.editHistoryStartTime.setOnClickListener {
            val timePicker = TimePickerDialog(
                this, R.style.TimePicker,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    //TimePicker 특성 상 한자리 시간 입력에 대한 대응을 해줘야 함
                    historyTime =
                        if (hourOfDay < 10) "0${hourOfDay}시"
                        else hourOfDay.toString() + "시"
                    historyTime +=
                        if (minute < 10) " 0${minute}분"
                        else " ${minute}분"

                    binding.editHistoryStartTime.text =historyTime.toString()
                    matchingEndTime = historyTime.toString()

                }, hour, minute, false
            )


            timePicker.show()

            val view: ViewGroup.MarginLayoutParams =
                timePicker.getButton(Dialog.BUTTON_POSITIVE).layoutParams as ViewGroup.MarginLayoutParams
            view.leftMargin = 16
            timePicker.getButton(Dialog.BUTTON_NEGATIVE)
                .setBackgroundColor(Color.parseColor("#00000000"))
            timePicker.getButton(Dialog.BUTTON_NEGATIVE)
                .setTextColor(Color.parseColor("#293263"))

            timePicker.getButton(Dialog.BUTTON_POSITIVE)
                .setBackgroundColor(Color.parseColor("#00000000"))
            timePicker.getButton(Dialog.BUTTON_POSITIVE)
                .setTextColor(Color.parseColor("#293263"))
        }

        // 히스토리 종료 시간 입력
        binding.editHistoryEndTime.setOnClickListener {

            val timePicker = TimePickerDialog(
                this, R.style.TimePicker,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    //TimePicker 특성 상 한자리 시간 입력에 대한 대응을 해줘야 함
                    historyTime =
                        if (hourOfDay < 10) "0${hourOfDay}시"
                        else hourOfDay.toString() + "시"
                    historyTime +=
                        if (minute < 10) " 0${minute}분"
                        else " ${minute}분"

                    binding.editHistoryEndTime.text = historyTime.toString()
                    matchingStartTime = historyTime.toString()

                }, hour, minute, false
            )
            timePicker.show()


            val view: ViewGroup.MarginLayoutParams =
                timePicker.getButton(Dialog.BUTTON_POSITIVE).layoutParams as ViewGroup.MarginLayoutParams
            view.leftMargin = 16
            timePicker.getButton(Dialog.BUTTON_NEGATIVE)
                .setBackgroundColor(Color.parseColor("#00000000"))
            timePicker.getButton(Dialog.BUTTON_NEGATIVE)
                .setTextColor(Color.parseColor("#293263"))

            timePicker.getButton(Dialog.BUTTON_POSITIVE)
                .setBackgroundColor(Color.parseColor("#00000000"))
            timePicker.getButton(Dialog.BUTTON_POSITIVE)
                .setTextColor(Color.parseColor("#293263"))
        }
    }

    private fun matchCrateClickListener() {

        // 작성 완료 및 업로드 버튼 눌렀을 때 진입
        if (binding.editHistoryTitle.text.isEmpty() || binding.editHistoryPlace.text.isEmpty() || binding.editHistoryDate.text == "매칭 날짜") {
            if (binding.editHistoryTitle.text.isEmpty()) binding.editHistoryTitle.error = "제목은 필수입력 항목입니다."
            if (binding.editHistoryPlace.text.isEmpty()) binding.editHistoryPlace.error = "장소명은 필수입력 항목입니다."
            if (binding.editHistoryDate.text == "매칭 날짜") {
                Toast.makeText(this, "날짜를 입력해주세요", Toast.LENGTH_LONG).show()
            }

        } else {

            matchingStartTime = matchingStartTime!!.replace("시 ", ":").replace("분", ":00")
            matchingEndTime = matchingEndTime!!.replace("시 ", ":").replace("분", ":00")

            viewModel.reservesEditRetrofit(ReservesEdit(
                reserveId!!.toInt(),
                title.toString(),
                content.toString(),
//                recruit!!.toInt(),
                sports.toString(),
                matchingStartTime.toString() ,
                matchingEndTime.toString(),
                matchingDate.toString(),
                place.toString(),
//                genderMap[gender].toString()
            ))

            viewModel.retrofitReservesEditText.observe(this){
                viewModel.retrofitReservesEditText.value.let {
                    if (it == true){
                        val intent = Intent(this@MatchingUpdateActivity, MatchingDetailActivity::class.java)
                        intent.apply {
                            this.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        }
                        intent.putExtra("reserveId", reserveId)
                        intent.putExtra("userId", userId)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }

    }
}
