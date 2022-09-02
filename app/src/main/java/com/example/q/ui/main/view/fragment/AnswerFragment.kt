package com.example.q.ui.main.view.fragment

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.q.R
import com.example.q.data.model.Question
import com.example.q.databinding.FragmentAnswersBinding
import com.example.q.databinding.FragmentQuestionBinding
import com.example.q.ui.main.viewmodel.QuestionsViewModel
import com.example.q.utils.AppClass
import com.example.q.utils.Consts
import com.example.q.utils.PermissionHelper
import com.example.q.utils.Utils


class AnswerFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentAnswersBinding
    lateinit var viewModel: QuestionsViewModel
    private lateinit var question: Question

    companion object {
        fun newInstance(): AnswerFragment {
            return AnswerFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAnswersBinding.inflate(layoutInflater)

        viewModel =
            ViewModelProvider(requireActivity()).get(QuestionsViewModel::class.java)

        binding.FragmentAnswersOptionA.setOnClickListener(this)
        binding.FragmentAnswersOptionB.setOnClickListener(this)
        binding.FragmentAnswersOptionC.setOnClickListener(this)
        binding.FragmentAnswersOptionD.setOnClickListener(this)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.question!!.observe(requireActivity(), Observer {

            question = it

            showAnswerOptions()

        })

    }

    private fun showAnswerOptions() {

        binding.FragmentAnswersOptionA.text = question.optionA
        binding.FragmentAnswersOptionB.text = question.optionB
        binding.FragmentAnswersOptionC.text = question.optionC
        binding.FragmentAnswersOptionD.text = question.optionD

    }

    override fun onClick(p0: View?) {
        checkAnswer((p0 as Button).text.toString())
    }


      /*  when (p0) {
            binding.FragmentAnswersOptionA -> {
                checkAnswer(question.optionA)
            }
            binding.FragmentAnswersOptionB -> {
                checkAnswer(question.optionB)
            }
            binding.FragmentAnswersOptionC -> {
                checkAnswer(question.optionC)
            }
            binding.FragmentAnswersOptionB -> {
                checkAnswer(question.optionD)
            }


        }*/

    fun checkAnswer(selectedAnswer: String) {
        if (selectedAnswer == question.correctAnswer) {
            Log.d("sdfsd", "correct")
        } else {
            Toast.makeText(AppClass.applicationContext(), "Wrong answer", Toast.LENGTH_LONG).show()
        }
    }


}