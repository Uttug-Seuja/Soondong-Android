package com.junjange.soondong.ui.matching_edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.junjange.soondong.R
import com.junjange.soondong.databinding.ActivityMatchingEditBinding
import com.junjange.soondong.databinding.ActivitySportsBinding
import com.junjange.soondong.utils.MyApplication

class SportsActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySportsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.sportsSoccerBtn.setOnClickListener {
            MyApplication.prefs.setString("sports", "축구")
            val intent = Intent(applicationContext, MatchingEditActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

        binding.sportsFutsalBtn.setOnClickListener {

            MyApplication.prefs.setString("sports", "풋살")
            val intent = Intent(applicationContext, MatchingEditActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

            startActivity(intent)
            finish()
        }
        binding.sportsBasketballBtn.setOnClickListener {
            MyApplication.prefs.setString("sports", "농구")
            val intent = Intent(applicationContext, MatchingEditActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

            startActivity(intent)
            finish()
        }

        binding.sportsRunningBtn.setOnClickListener {
            MyApplication.prefs.setString("sports", "런닝")
            val intent = Intent(applicationContext, MatchingEditActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

            startActivity(intent)
            finish()
        }



    }
}