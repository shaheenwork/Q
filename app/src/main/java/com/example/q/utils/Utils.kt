package com.example.q.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.q.utils.Consts.Companion.path
import java.io.File


interface Utils {

    companion object {
        fun createFolder() {

            val myDir = File(path)
            if (!myDir.exists()) {
                myDir.mkdir()
            }
            Log.d("asdf", path)


        }

        fun loadImage(path: String, imageView: ImageView) {
            val file = File(path)
            val imageUri: Uri = Uri.fromFile(file)
            Glide.with(AppClass.applicationContext())
                .load(imageUri)
                .into(imageView)
        }

        fun playAudio(mediaPlayer: MediaPlayer, path: String, isLooping: Boolean) {
            mediaPlayer.reset();
            // in recursive version
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.isLooping = isLooping
            mediaPlayer.start();
        }



    }


}