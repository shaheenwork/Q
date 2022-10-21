package com.example.q.utils

import com.example.q.R

public interface Consts {
    companion object{
        const val statusCompleted:Int = 1
        const val statusIncomplete:Int = 0



        //Shared preferences keys
        const val KEY_LEVEL:String = "level"


        //animation filenames
        const val ANIM_CORRECT:String = "anim2.json"
        const val ANIM_WRONG:String = "anim3.json"



        //type of questions
        const val TYPE_VIDEO:Int = 1
        const val TYPE_AUDIO_BGM:Int = 2
        const val TYPE_AUDIO_DIALOGUE:Int = 3
        const val TYPE_IMAGE:Int = 4
        const val TYPE_GIF:Int = 5



        val path = AppClass.applicationContext()
            .getExternalFilesDir(null).toString() + "/" + AppClass.applicationContext()
            .getString(R.string.app_name)
        const val FOLDER_NAME:String = "level-"


    }
}