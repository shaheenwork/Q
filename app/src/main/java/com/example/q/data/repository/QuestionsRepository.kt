package com.example.q.data.repository

import androidx.lifecycle.LiveData
import com.example.q.utils.Consts
import com.example.q.utils.PrefManger
import com.example.q.data.model.Question
import com.example.q.database.QuestionsDao
import com.example.q.database.QuestionsDatabase

class QuestionsRepository() {
    var db: QuestionsDao = QuestionsDatabase.getInstance()?.questionsDao()!!
    var getNextQuestion: LiveData<Question> = db.getNextQuestion(getUserLevel())


    fun insertQuestionToDb(user: Question) {
        db.insert(user)
    }

    fun deleteQuestionFromDb(user: Question) {
        db.delete(user)
    }

    fun updateQuestion(question: Question) {
        db.update(question)
    }


    private fun getUserLevel() =
        PrefManger.getInt(Consts.KEY_LEVEL, 1)

    private fun setUserLevel(level: Int) {
        PrefManger.putInt(Consts.KEY_LEVEL, level)
    }


}