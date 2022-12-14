package com.example.q.ui.main.view.fragment

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.q.R
import com.example.q.data.model.Question
import com.example.q.databinding.FragmentQuestionBinding
import com.example.q.ui.main.viewmodel.QuestionsViewModel
import com.example.q.utils.AppClass
import com.example.q.utils.Consts
import com.example.q.utils.PermissionHelper
import com.example.q.utils.Utils


class QuestionFragment : Fragment(), PermissionHelper.PermissionListener {

    lateinit var binding: FragmentQuestionBinding
    lateinit var viewModel: QuestionsViewModel
    private lateinit var permissionHelper: PermissionHelper
    private val mediaPlayer = MediaPlayer()
    private lateinit var question: Question
    private lateinit var questionPath: String

    companion object {
        fun newInstance(): QuestionFragment {
            return QuestionFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentQuestionBinding.inflate(layoutInflater)

        viewModel =
            ViewModelProvider(requireActivity()).get(QuestionsViewModel::class.java)

        permissionHelper = PermissionHelper(this, this)

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

        /*viewModel.getNextQuestion().observe(requireActivity()) {
            question = it
            showQuestion()
        }*/

        getQuestion()

    }

    private fun getQuestion() {
        viewModel.question.observe(requireActivity(), Observer {

            question = it
            showQuestion()

        })
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
    }

    private fun showQuestion() {

        //     viewModel.currentQuestion(question) //for passing question details to AnswerFragment

        binding.fragmentQuestionTextview.text = question.question
        questionPath =
            Consts.path + "/" + Consts.FOLDER_NAME + question.level + "/" + question.file_name
        when (question.type) {
            Consts.TYPE_IMAGE -> {
                setVisibilityOfQuestionView(binding.FragmentQuestionImageview)
                Utils.loadImage(questionPath, binding.FragmentQuestionImageview)
            }
            Consts.TYPE_AUDIO_BGM -> {

                if (permissionHelper.checkPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    showPermissionExplanationDialog()
                } else {
                    manageAudioTypeQuestion()
                }
            }
            Consts.TYPE_VIDEO -> {
                manageVideoTypeQuestion()
            }
            Consts.TYPE_AUDIO_DIALOGUE -> {
                manageDialogTypeQuestion()
            }
        }
    }

    private fun manageVideoTypeQuestion() {
        setVisibilityOfQuestionView(binding.FragmentQuestionVideoView)
        binding.FragmentQuestionVideoView.setVideoURI(Uri.parse(questionPath))
        binding.FragmentQuestionVideoView.start()
        binding.FragmentQuestionVideoView.setOnPreparedListener { it.isLooping = true }
    }

    private fun manageAudioTypeQuestion() {
        setVisibilityOfQuestionView(binding.FragmentQuestionAudioView)
        Utils.playAudio(mediaPlayer, questionPath, true)
        showBGMVisualisation()
    }
    private fun manageDialogTypeQuestion() {
        setVisibilityOfQuestionView(binding.FragmentQuestionDialogView)
        Utils.playAudio(mediaPlayer, questionPath, true)
        showDialogVisualisation()
    }

    private fun setVisibilityOfQuestionView(view_to_be_shown: View) {

        // everything invisible except view_to_be_shown

        view_to_be_shown.visibility = View.VISIBLE
        when (view_to_be_shown) {
            binding.FragmentQuestionImageview -> {
                binding.FragmentQuestionVideoView.visibility = View.GONE
                binding.FragmentQuestionDialogView.visibility = View.GONE
                binding.FragmentQuestionAudioView.visibility = View.GONE

                binding.FragmentQuestionVideoView.stopPlayback()
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.reset()
                }
            }
            binding.FragmentQuestionAudioView -> {
                binding.FragmentQuestionVideoView.visibility = View.GONE
                binding.FragmentQuestionVideoView.stopPlayback()
                binding.FragmentQuestionImageview.visibility = View.GONE
                binding.FragmentQuestionDialogView.visibility = View.GONE
            }
            binding.FragmentQuestionDialogView -> {
                binding.FragmentQuestionVideoView.visibility = View.GONE
                binding.FragmentQuestionVideoView.stopPlayback()
                binding.FragmentQuestionImageview.visibility = View.GONE
                binding.FragmentQuestionAudioView.visibility = View.GONE
            }
            binding.FragmentQuestionVideoView -> {
                binding.FragmentQuestionAudioView.visibility = View.GONE
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.reset()
                }
                binding.FragmentQuestionImageview.visibility = View.GONE
                binding.FragmentQuestionDialogView.visibility = View.GONE
            }
        }

    }

    private fun showBGMVisualisation() {
        binding.FragmentQuestionAudioView.setColor(
            ContextCompat.getColor(
                AppClass.applicationContext(),
                R.color.black
            )
        );
        binding.FragmentQuestionAudioView.setPlayer(mediaPlayer.audioSessionId);
    }

    private fun showDialogVisualisation() {

        binding.FragmentQuestionDialogView.setColor(
            ContextCompat.getColor(
                AppClass.applicationContext(),
                R.color.black
            )
        );
        binding.FragmentQuestionDialogView.setPlayer(mediaPlayer.audioSessionId);
    }

    override fun shouldShowRationaleInfo() {
        showPermissionExplanationDialog()
    }

    override fun isPermissionGranted(isGranted: Boolean) {

        if (isGranted) {
            //  manageAudioTypeQuestion()
        } else {
            showPermissionExplanationDialog()
        }
    }

    private fun showPermissionExplanationDialog() {
        println("shouldShowRationaleInfo...................")
        val dialogBuilder = AlertDialog.Builder(requireContext())

        // set message of alert dialog
        dialogBuilder.setMessage("permission is Required")
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("OK") { dialog, id ->
                dialog.cancel()
                permissionHelper.launchPermissionDialog(
                    Manifest.permission.RECORD_AUDIO
                )
            }
        // negative button text and action
        /* .setNegativeButton("Exit", DialogInterface.OnClickListener { dialog, id ->
             dialog.cancel()
         })*/

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("AlertDialogExample")
        // show alert dialog
        alert.show()
    }


}