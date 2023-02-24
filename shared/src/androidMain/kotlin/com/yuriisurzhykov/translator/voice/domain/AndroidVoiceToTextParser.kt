package com.yuriisurzhykov.translator.voice.domain

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import com.yuriisurzhykov.translator.R
import com.yuriisurzhykov.translator.core.domain.CommonStateFlow
import com.yuriisurzhykov.translator.core.domain.asCommonStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class AndroidVoiceToTextParser(
    private val app: Application
) : VoiceToTextParser, RecognitionListener {

    private val mState = MutableStateFlow(VoiceParseState())
    private val state = mState.asCommonStateFlow()
    private val recognizer = SpeechRecognizer.createSpeechRecognizer(app)

    override fun startListening(languageCode: String) {
        mState.update { VoiceParseState() }

        if (!SpeechRecognizer.isRecognitionAvailable(app)) {
            mState.update { it.copy(error = app.getString(R.string.error_speech_recognition_unavailable)) }
            return
        }

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, languageCode)
        }
        recognizer.setRecognitionListener(this)
        recognizer.startListening(intent)
        mState.update { it.copy(isSpeaking = true) }
    }

    override fun stopListening() {
        mState.update { VoiceParseState() }
        recognizer.stopListening()
    }

    override fun cancel() {
        recognizer.cancel()
    }

    override fun reset() {
        mState.value = VoiceParseState()
    }

    override fun state(): CommonStateFlow<VoiceParseState> = state

    override fun onReadyForSpeech(params: Bundle?) {
        mState.update { it.copy(error = null) }
    }

    override fun onBeginningOfSpeech() = Unit

    override fun onRmsChanged(rmsdB: Float) {
        mState.update { it.copy(powerRatio = rmsdB * (1f / (12f - (-2f)))) }
    }

    override fun onBufferReceived(buffer: ByteArray?) = Unit


    override fun onEndOfSpeech() {
        mState.update { it.copy(isSpeaking = false) }
    }

    override fun onError(error: Int) {
        if (error == SpeechRecognizer.ERROR_CLIENT) {
            return
        }
        mState.update {
            it.copy(
                error = app.getString(R.string.error_recognition_error).format(error)
            )
        }
    }

    override fun onResults(results: Bundle?) {
        results
            ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            ?.getOrNull(0)
            ?.let { text ->
                mState.update { it.copy(result = text) }
            }
    }

    override fun onPartialResults(partialResults: Bundle?) = Unit

    override fun onEvent(eventType: Int, params: Bundle?) = Unit
}