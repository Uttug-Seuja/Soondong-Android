package com.junjange.soondong.ui.matching_edit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.junjange.soondong.databinding.ActivityGenderBinding
import com.junjange.soondong.utils.MyApplication

class GenderActivity : AppCompatActivity() {

    private val binding by lazy { ActivityGenderBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.genderAllBtn.setOnClickListener {
            MyApplication.prefs.setString("gender", "남녀 모두")

            val intent = Intent(applicationContext, MatchingEditActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

            startActivity(intent)
            finish()

        }

        binding.genderManBtn.setOnClickListener {
            MyApplication.prefs.setString("gender", "남자만")

            val intent = Intent(applicationContext, MatchingEditActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()

        }
        binding.genderWomanBtn.setOnClickListener {
            MyApplication.prefs.setString("gender", "여자만")
            val intent = Intent(applicationContext, MatchingEditActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()

        }


    }
}