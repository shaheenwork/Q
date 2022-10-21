package com.example.q.ui.main.viewmodel

import android.content.Context
import android.media.metrics.Event
import android.os.Handler
import android.util.EventLog
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.q.utils.Consts.Companion.TYPE_AUDIO_BGM
import com.example.q.utils.Consts.Companion.TYPE_AUDIO_DIALOGUE
import com.example.q.utils.Consts.Companion.TYPE_IMAGE
import com.example.q.utils.Consts.Companion.TYPE_VIDEO
import com.example.q.utils.Consts.Companion.statusIncomplete
import com.example.q.data.model.Question
import com.example.q.data.repository.QuestionsRepository
import com.example.q.utils.AppClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class QuestionsViewModel : ViewModel() {

    var repository = QuestionsRepository()

  val question = MutableLiveData<Question>()

    fun getNextQuestion() = repository.getNextQuestion

    fun currentQuestion(question: Question) {
        this.question.value = question
    }

    fun addQuestions() {


        viewModelScope.launch(Dispatchers.IO) {

            withContext(Dispatchers.IO) {
                repository.insertQuestionToDb(
                    Question(
                        TYPE_IMAGE,
                        "saul_car.jpg",
                        "Who's car is this ?",
                        "James Bond",
                        "Saul Goodman",
                        "Dexter Morgan",
                        "Detective Bosch",
                        1,
                        "Saul Goodman",
                        statusIncomplete
                    )
                )



                repository.insertQuestionToDb(
                    Question(
                        TYPE_AUDIO_BGM,
                        "got_bgm.mp3",
                        "Identify the BGM played",
                        "Game of thrones",
                        "Money Heist",
                        "Prison Break",
                        "Fargo",
                        1,
                        "Game of thrones",
                        statusIncomplete
                    )
                )




                repository.insertQuestionToDb(
                    Question(
                        TYPE_VIDEO,
                        "dark_cave.mp4",
                        "Identify the show",
                        "West World",
                        "Bosch",
                        "Dark",
                        "Dexter",
                        1,
                        "Dark",
                        statusIncomplete
                    )
                )




                repository.insertQuestionToDb(
                    Question(
                        TYPE_AUDIO_DIALOGUE,
                        "danger.mp3",
                        "Complete the dialog",
                        "I am the danger",
                        "I am not in danger",
                        "I am in danger",
                        "Danger, what danger ?",
                        1,
                        "I am the danger",
                        statusIncomplete
                    )
                )


            }

        }


    }

    fun updateQuestion(question: Question) {
        repository.updateQuestion(question)
    }

}