package com.yuriisurzhykov.translator.voice.presentation

interface DisplayState {

    class WaitingToTalk : DisplayState
    class Speaking : DisplayState
    class DisplayingResults : DisplayState
    class Error : DisplayState
}