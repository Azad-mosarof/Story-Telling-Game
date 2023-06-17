package com.example.storytellinggame

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.storytellinggame.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    var TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    // For Text to Speech
    private var tts: TextToSpeech? = null
    private lateinit var tapMediaPlayer: MediaPlayer
    private lateinit var successMediaPlayer: MediaPlayer
    private lateinit var failMediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun takeInput(){
        touch = 0
        TOUCH_0_TIME = Calendar.getInstance().timeInMillis.toInt()
        val date = Calendar.getInstance().time
        binding.touchView.setOnClickListener{
            //paly touch sound
            tapMediaPlayer = MediaPlayer.create(this, R.raw.tap)
            tapMediaPlayer.setVolume(1f, 1f)
            tapMediaPlayer.start()
            touch +=1
            when(touch) {
                1 -> {
                    TOUCH_1_TIME = Calendar.getInstance().timeInMillis.toInt()
                }
                2 -> {
                    TOUCH_2_TIME = Calendar.getInstance().timeInMillis.toInt()
                }
                3 -> {
                    TOUCH_3_TIME = Calendar.getInstance().timeInMillis.toInt()
                }
                4 -> {
                    TOUCH_4_TIME = Calendar.getInstance().timeInMillis.toInt()
                }
                5 -> {
                    TOUCH_5_TIME = Calendar.getInstance().timeInMillis.toInt()
                }
                6 -> {
                    TOUCH_6_TIME = Calendar.getInstance().timeInMillis.toInt()
                }
            }
        }
    }

    private fun initUI() {
        tts = TextToSpeech(this@MainActivity) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(destLanguage)
                //change the reading speed of tts
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e(TAG, "The Language not supported!")
                } else {
                    tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                        override fun onStart(utteranceId: String) {
                            handleOnStartUtterance(utteranceId)
                        }

                        override fun onDone(utteranceId: String) {
                            handleOnDoneUtterance(utteranceId)
                        }

                        @Deprecated("Deprecated in Java")
                        override fun onError(utteranceId: String) {
                            handleOnErrorUtterance(utteranceId)
                        }
                    })

                    if (allPermissionsGranted()) {
                        playInstruction(INSTRUCTION_5, INSTRUCTION_5_ID)
                        Toast.makeText(this, "All permission are granted", Toast.LENGTH_SHORT).show()
                    } else {
                        ActivityCompat.requestPermissions(
                            this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
                        )
                    }
                }
            }
        }
    }

    private fun playInstruction(instructionText: String, instructionId: String) {
        tts!!.speak(instructionText, TextToSpeech.QUEUE_FLUSH, null, instructionId)
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Permissions granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            }
        }
    }

    private fun handleOnStartUtterance(utteranceId: String) {
        Log.d(TAG, "TTS Started $utteranceId")
        when (utteranceId) {
            else -> {
                // Do nothing if Instruction ID don't match
            }
        }
    }

    private fun handleOnDoneUtterance(utteranceId: String) {
        Log.d(TAG, "TTS Done $utteranceId")
        when (utteranceId) {
            else -> {
                // Do nothing if Instruction ID don't match
            }
        }
    }

    private fun handleOnErrorUtterance(utteranceId: String) {
        Log.d(TAG, "TTS Error $utteranceId")
        when (utteranceId) {
            else -> {
                // Do nothing if Instruction ID don't match
            }
        }
    }

    companion object {
        private const val TAG = "BlindAssistant"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            ).toTypedArray()
    }

    fun generateStory(animals: List<String>, vegFoods: List<String>, nonVegFoods: List<String>): String {
        val random = java.util.Random()
        val animalIndex = random.nextInt(animals.size)

        return ""
    }

}