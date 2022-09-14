package com.example.q.ui.main.view.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.example.q.databinding.ActivityMainBinding
import com.example.q.ui.main.viewmodel.QuestionsViewModel
import com.example.q.utils.Utils


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)




        setContentView(binding.root)

        Utils.createFolder()


        val viewModel: QuestionsViewModel =
            ViewModelProvider(this)[QuestionsViewModel::class.java]
        viewModel.addQuestions()


    }


}