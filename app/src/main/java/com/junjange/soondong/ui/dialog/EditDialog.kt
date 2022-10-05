package com.junjange.soondong.ui.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.junjange.soondong.R

import android.annotation.SuppressLint
import android.content.Intent

import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.junjange.soondong.databinding.ActivityEditDialogBinding
import com.junjange.soondong.databinding.ActivityGenderBinding
import com.junjange.soondong.ui.matching_edit.MatchingEditActivity
import com.junjange.soondong.utils.MyApplication

class EditDialog : AppCompatActivity() {
    private val binding by lazy { ActivityEditDialogBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.dialog = this
        binding.lifecycleOwner = this



        setSupportActionBar(binding.mainToolbar) // 툴바를 액티비티의 앱바로 지정
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게


        binding.startBtn.setOnClickListener {


            startActivity(Intent(this, MatchingEditActivity::class.java))
            finish()


        }

        binding.stopBtn.setOnClickListener {


            MyApplication.prefs.setString("title", "")
            MyApplication.prefs.setString("sports", "종목 선택")
            MyApplication.prefs.setString("place", "")
            MyApplication.prefs.setString("recruit", "")
            MyApplication.prefs.setString("gender", "모집 성별")
            MyApplication.prefs.setString("matchingDate", "매칭 날짜")
            MyApplication.prefs.setString("matchingStartTime", "매칭 시작시간")
            MyApplication.prefs.setString("matchingEndTime", "매칭 종료시간")
            MyApplication.prefs.setString("content", "")


            startActivity(Intent(this, MatchingEditActivity::class.java))
            finish()



        }





    }

    @SuppressLint("QueryPermissionsNeeded")
    fun share(content: String) {
        val intent = Intent(Intent.ACTION_SEND) // 공유하는 인텐트 생성
            .apply {
                type = "text/plain" // 데이터 타입 설정
                putExtra(Intent.EXTRA_TEXT, content) // 보낼 내용 설정
            }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "초대 메세지 보내기"))
        } else {
            Toast.makeText(this, "초대 메세지를 전송할 수 없습니다", Toast.LENGTH_LONG).show()
        }
    }
}