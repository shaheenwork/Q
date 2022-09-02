package com.example.q.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.q.utils.Consts
import com.example.q.data.model.Question

@Dao
interface QuestionsDao {

    @Insert
    fun insert(user: Question)

    @Update
    fun update(user: Question)

    @Delete
    fun delete(user: Question)

    @Query("select * from questionsTable where level=:currentLevel and status="+ Consts.statusIncomplete +" limit 1")
    fun getNextQuestion(currentLevel:Int): LiveData<Question>

    @Query("select * from questionsTable where id=:questionId")
    fun getQuestion(questionId:Int): Question


}