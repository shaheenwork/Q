package com.example.q.ui.main.view.fragment


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.q.R
import com.example.q.data.model.Question
import com.example.q.databinding.AnswerAninmationBinding
import com.example.q.databinding.FragmentAnswersBinding
import com.example.q.ui.main.viewmodel.QuestionsViewModel
import com.example.q.utils.Consts
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
            ViewModelProvider(requireActivity())[QuestionsViewModel::class.java]

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

        /* viewModel.question.observe(requireActivity(), Observer {

             question = it

             showAnswerOptions()

         })*/

        //   getNextQuestion()


    }

    override fun onResume() {
        super.onResume()
        getNextQuestion()

    }

    private fun getNextQuestion() {
        viewModel.getNextQuestion().observe(requireActivity()) {
            question = it
            viewModel.currentQuestion(question) //for passing question details to QuestionFragment
            showAnswerOptions()
        }
    }

    private fun showAnswerOptions() {

        binding.FragmentAnswersOptionA.text = question.optionA
        binding.FragmentAnswersOptionB.text = question.optionB
        binding.FragmentAnswersOptionC.text = question.optionC
        binding.FragmentAnswersOptionD.text = question.optionD

    }

    override fun onClick(view: View) {
        checkAnswer((view as Button).text.toString())
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

    private fun checkAnswer(selectedAnswer: String) {
        if (selectedAnswer == question.correctAnswer) {

            showAnimationDialog(true)
          //  getNextQuestion()
        } else {

            showAnimationDialog(false)


        }
    }

    private fun showAnimationDialog(answerCorrect: Boolean) {
        val dialogBinding = AnswerAninmationBinding.inflate(layoutInflater)
        val view = dialogBinding.root
        val dialog = Dialog(requireActivity(), R.style.dialog_theme)
        dialog.setContentView(view)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
        if (answerCorrect) {
            Utils.showLottieAnimation(dialogBinding.animationView, Consts.ANIM_CORRECT)
        } else {
            Utils.showLottieAnimation(dialogBinding.animationView, Consts.ANIM_WRONG)
        }

        dialog.setOnCancelListener {

            if (answerCorrect) {
                question.status = Consts.statusCompleted
                viewModel.updateQuestion(question)
                getNextQuestion()
            }

        }
    }

}