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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.junjange.soondong.R
import com.junjange.soondong.adapter.CalendarAdapter
import com.junjange.soondong.data.CalendarDateModel
import com.junjange.soondong.data.ReservesCreation
import com.junjange.soondong.databinding.ActivityMatchingEditBinding
import com.junjange.soondong.ui.main.MainActivity
import com.junjange.soondong.ui.matching_detail.MatchingDetailViewModel
import com.junjange.soondong.ui.signin.SigninActivity
import com.junjange.soondong.utils.HorizontalItemDecoration
import com.junjange.soondong.utils.MyApplication
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class MatchingEditActivity : AppCompatActivity(){

    private val binding by lazy { ActivityMatchingEditBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this, MatchingEditViewModel.Factory(application))[MatchingEditViewModel::class.java] }

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
    private val genderMap = hashMapOf<String, String>("?????? ??????" to "ALL", "?????????" to "MAN", "?????????" to "WOMAN" )
    private val sportsMap = hashMapOf<String, String>("??????" to "SOCCER", "??????" to "FUTSAL", "??????" to "RUNNING", "??????" to "BASKETBALL" )

    var historyTitle: String? = null
    var historyMood: String? = null
    var historyComment: String? = null
    var historyPlaceTitle: String? = null
    var historyUserID: Int? = null
    var historyCreatedAt: String? = null  // historyDate + historyTime
    var historyDate: String? = null
    var historyTime: String? = null

    // Date, Time Picker ?????? ????????? ????????? ?????? ?????? ??? ?????? ?????????
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

        setSupportActionBar(binding.mainToolbar) // ????????? ??????????????? ????????? ??????
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // ???????????? ?????? ??? ?????? ?????????
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24) // ????????? ????????? ??????
        supportActionBar?.setDisplayShowTitleEnabled(false) // ????????? ????????? ????????????

        title = MyApplication.prefs.getString("title", "")
        sports = MyApplication.prefs.getString("sports", "?????? ??????")
        place = MyApplication.prefs.getString("place", "")
        recruit = MyApplication.prefs.getString("recruit", "")
        gender = MyApplication.prefs.getString("gender", "?????? ??????")
        matchingDate = MyApplication.prefs.getString("matchingDate", "?????? ??????")
        matchingStartTime = MyApplication.prefs.getString("matchingStartTime", "?????? ????????????")
        matchingEndTime = MyApplication.prefs.getString("matchingEndTime", "?????? ????????????")
        content = MyApplication.prefs.getString("content", "")
        member = MyApplication.prefs.getString("memberId", "-1").toInt()


        binding.editHistoryTitle.setText(title)
        binding.sportsText.text = sports
        binding.editHistoryPlace.setText(place)
        binding.editHistoryRecruit.setText(recruit)
        binding.genderText.text = gender
        binding.editHistoryDate.text = matchingDate
        binding.editHistoryStartTime.text = matchingStartTime
        binding.editHistoryEndTime.text = matchingEndTime
        binding.editHistoryContent.setText(content)

        historyDate = matchingDate
        addTextChangedListener()
        setOnClickListener()
    }

    //???????????? ?????? ???????????? ?????? ??????
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    //???????????? ?????? ?????? ???
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

        // ???????????? ?????? ??????
        binding.editHistoryDateBtn.setOnClickListener {
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    historyDate =
                        year.toString() + "-"
                    //DatePicker ?????? ??? ??? ?????? ?????? ????????? ?????? ????????? ????????? ???
                    historyDate +=
                        if ((monthOfYear + 1) < 10) "0" + (monthOfYear + 1).toString() + "-"
                        else (monthOfYear + 1).toString() + "-"
                    historyDate +=
                        if (dayOfMonth < 10) "0$dayOfMonth"
                        else "$dayOfMonth"

                    Log.d("ttt123", historyDate.toString())

                    binding.editHistoryDate.text = historyDate.toString()
                    MyApplication.prefs.setString("matchingDate", historyDate.toString())


                    historyDate += "T"
                }, year, month, day
            )
            datePicker.show()
        }

        // ???????????? ?????? ?????? ??????
        binding.editHistoryStartTime.setOnClickListener {
            val timePicker = TimePickerDialog(
                this, R.style.TimePicker,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    //TimePicker ?????? ??? ????????? ?????? ????????? ?????? ????????? ????????? ???
                    historyTime =
                        if (hourOfDay < 10) "0${hourOfDay}???"
                        else hourOfDay.toString() + "???"
                    historyTime +=
                        if (minute < 10) " 0${minute}???"
                        else " ${minute}???"
                    binding.editHistoryStartTime.text =historyTime.toString()
                    MyApplication.prefs.setString("matchingStartTime", historyTime.toString())

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

        // ???????????? ?????? ?????? ??????
        binding.editHistoryEndTime.setOnClickListener {

            val timePicker = TimePickerDialog(
                this, R.style.TimePicker,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    //TimePicker ?????? ??? ????????? ?????? ????????? ?????? ????????? ????????? ???
                    historyTime =
                        if (hourOfDay < 10) "0${hourOfDay}???"
                        else hourOfDay.toString() + "???"
                    historyTime +=
                        if (minute < 10) " 0${minute}???"
                        else " ${minute}???"

                    binding.editHistoryEndTime.text = historyTime.toString()
                    MyApplication.prefs.setString("matchingEndTime", historyTime.toString())

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

        // ?????? ?????? ??? ????????? ?????? ????????? ??? ??????
            if (binding.editHistoryTitle.text.isEmpty() || binding.editHistoryPlace.text.isEmpty() || binding.editHistoryDate.text == "?????? ??????") {
                if (binding.editHistoryTitle.text.isEmpty()) binding.editHistoryTitle.error = "????????? ???????????? ???????????????."
                if (binding.editHistoryPlace.text.isEmpty()) binding.editHistoryPlace.error = "???????????? ???????????? ???????????????."
                if (binding.editHistoryDate.text == "?????? ??????") {
                    Toast.makeText(this, "????????? ??????????????????", Toast.LENGTH_LONG).show()
                }

            } else {
                matchingStartTime = MyApplication.prefs.getString("matchingStartTime", "")
                matchingStartTime = matchingStartTime!!.replace("??? ", ":").replace("???", ":00")
                matchingEndTime = MyApplication.prefs.getString("matchingEndTime", "")
                matchingEndTime = matchingEndTime!!.replace("??? ", ":").replace("???", ":00")
                matchingDate = MyApplication.prefs.getString("matchingDate", "")


                viewModel.reservesCreationRetrofit(ReservesCreation(
                    member!!.toInt(),
                    title.toString(),
                    content.toString(),
                    recruit!!.toInt(),
                    sportsMap[sports].toString(),
                    matchingStartTime.toString() ,
                    matchingEndTime.toString(),
                    matchingDate.toString(),
                    place.toString(),
                    genderMap[gender].toString()
                ))

                viewModel.reservesCreationRetrofit.observe(this){
                    viewModel.reservesCreationRetrofit.value.let {
                        if (it == true){
                            MyApplication.prefs.setString("title", "")
                            MyApplication.prefs.setString("sports", "?????? ??????")
                            MyApplication.prefs.setString("place", "")
                            MyApplication.prefs.setString("recruit", "")
                            MyApplication.prefs.setString("gender", "?????? ??????")
                            MyApplication.prefs.setString("matchingDate", "?????? ??????")
                            MyApplication.prefs.setString("matchingStartTime", "?????? ????????????")
                            MyApplication.prefs.setString("matchingEndTime", "?????? ????????????")
                            MyApplication.prefs.setString("content", "")
                            finish()
                        }
                    }
                }
            }
    }
}
