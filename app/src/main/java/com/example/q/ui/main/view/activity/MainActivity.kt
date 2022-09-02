package com.example.q.ui.main.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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