package com.example.q.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.q.data.model.Question
import com.example.q.utils.AppClass

@Database(entities = [Question::class], version = 1)
abstract class QuestionsDatabase:RoomDatabase(){

    abstract fun questionsDao(): QuestionsDao

    companion object{
        private var INSTANCE: QuestionsDatabase? = null

        fun getInstance(): QuestionsDatabase?{

            if (INSTANCE ==null){
                synchronized(QuestionsDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        AppClass.applicationContext(),
                        QuestionsDatabase::class.java,
                        "QuestionsDB"
                    ).allowMainThreadQueries().build()
                }
            }

            return INSTANCE

        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}