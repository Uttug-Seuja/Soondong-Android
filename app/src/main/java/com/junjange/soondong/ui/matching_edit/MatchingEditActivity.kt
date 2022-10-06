package com.junjange.soondong.ui.matching_edit

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.junjange.soondong.R
import com.junjange.soondong.adapter.CalendarAdapter
import com.junjange.soondong.data.CalendarDateModel
import com.junjange.soondong.databinding.ActivityMatchingEditBinding
import com.junjange.soondong.ui.main.MainActivity
import com.junjange.soondong.utils.HorizontalItemDecoration
import com.junjange.soondong.utils.MyApplication
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class MatchingEditActivity : AppCompatActivity(){

    private val binding by lazy { ActivityMatchingEditBinding.inflate(layoutInflater) }
    private val viewModel : MatchingEditViewModel by viewModels()

    private var title : String? = null
    private var sports : String? = null
    private var place : String? = null
    private var recruit : String? = null
    private var gender : String? = null
    private var matchingDate : String? = null
    private var matchingStartTime : String? = null
    private var matchingEndTime : String? = null
    private var content : String? = null

    var historyTitle: String? = null
    var historyMood: String? = null
    var historyComment: String? = null
    var historyPlaceTitle: String? = null
    var historyUserID: Int? = null
    var historyCreatedAt: String? = null  // historyDate + historyTime
    var historyDate: String? = null
    var historyTime: String? = null

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

        setSupportActionBar(binding.mainToolbar) // 툴바를 액티비티의 앱바로 지정
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게


        title = MyApplication.prefs.getString("title", "")
        sports = MyApplication.prefs.getString("sports", "종목 선택")
        place = MyApplication.prefs.getString("place", "")
        recruit = MyApplication.prefs.getString("recruit", "")
        gender = MyApplication.prefs.getString("gender", "모집 성별")
        matchingDate = MyApplication.prefs.getString("matchingDate", "매칭 날짜")
        matchingStartTime = MyApplication.prefs.getString("matchingStartTime", "매칭 시작시간")
        matchingEndTime = MyApplication.prefs.getString("matchingEndTime", "매칭 종료시")
        content = MyApplication.prefs.getString("content", "")


        binding.editHistoryTitle.setText(title)
        binding.sportsText.text = sports
        binding.editHistoryPlace.setText(place)
        binding.editHistoryRecruit.setText(recruit)
        binding.genderText.text = gender
        binding.editHistoryDate.text = matchingDate
        binding.editHistoryStartTime.text = matchingStartTime
        binding.editHistoryEndTime.text = matchingEndTime
        binding.editHistoryContent.setText(content)


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


            R.id.action_search -> {
                startActivity(Intent(this, MainActivity::class.java))

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
                MyApplication.prefs.setString("title", title.toString())


            }
        })


        binding.editHistoryPlace.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {

                place = binding.editHistoryPlace.text.toString()
                MyApplication.prefs.setString("place", place.toString())


            }
        })

        binding.editHistoryRecruit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {

                recruit = binding.editHistoryRecruit.text.toString()
                MyApplication.prefs.setString("recruit", recruit.toString())


            }
        })

        binding.editHistoryContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {

                content = binding.editHistoryContent.text.toString()
                MyApplication.prefs.setString("content", content.toString())


            }
        })


    }




    private fun setOnClickListener(){



        binding.sportsSelectBtn.setOnClickListener {
            startActivity( Intent(this, SportsActivity::class.java))
        }

        binding.genderSelectBtn.setOnClickListener {
            startActivity( Intent(this, GenderActivity::class.java))
        }

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
                    MyApplication.prefs.setString("editHistoryDate", historyDate.toString())


                    historyDate += "T"
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
                        if (hourOfDay < 10) "0$hourOfDay:"
                        else hourOfDay.toString() + ":"
                    historyTime +=
                        if (minute < 10) "0$minute"
                        else minute.toString()

                    binding.editHistoryStartTime.text = hourOfDay.toString() + "시 " + minute.toString() + "분"
                    MyApplication.prefs.setString("matchingStartTime", hourOfDay.toString() + "시 " + minute.toString() + "분")

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
                        if (hourOfDay < 10) "0$hourOfDay:"
                        else hourOfDay.toString() + ":"
                    historyTime +=
                        if (minute < 10) "0$minute"
                        else minute.toString()

                    binding.editHistoryEndTime.text = hourOfDay.toString() + "시 " + minute.toString() + "분"
                    MyApplication.prefs.setString("matchingEndTime", hourOfDay.toString() + "시 " + minute.toString() + "분")

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


        // 작성 완료 및 업로드 버튼 눌렀을 때 진입
//        binding.buttonUploadHistory.setOnClickListener {
//            if (binding.editHistoryTitle.text.isEmpty() || binding.editHistoryPlace.text.isEmpty() || historyDate == null || historyTime == null) {
//                if (binding.editHistoryTitle.text.isEmpty()) binding.editHistoryTitle.error = "제목은 필수입력 항목입니다."
//                if (binding.editHistoryPlace.text.isEmpty()) binding.editHistoryPlace.error = "장소명은 필수입력 항목입니다."
//                if (historyDate == null) {
//                    Toast.makeText(this, "날짜를 입력해주세요", Toast.LENGTH_LONG).show()
//                }
//                if (historyTime == null) {
//                    Toast.makeText(this, "시간을 입력해주세요", Toast.LENGTH_LONG).show()
//                }
//            } else {
//                historyTitle = binding.editHistoryTitle.text.toString()
//                historyComment = binding.editHistoryContent.text.toString()
//                historyPlaceTitle = binding.editHistoryPlace.text.toString()
//                historyCreatedAt = historyDate + historyTime
//
//                // 날짜 및 시각은 LocalDateTime 객체 형태로 Request 해야함
//                val localTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    LocalDateTime.parse(historyCreatedAt)
//                } else {
//                    TODO("VERSION.SDK_INT < O")
//                }
//
//            }
//        }

    }
}
