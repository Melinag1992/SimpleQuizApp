package com.example.quizapp.domain.models

import com.example.quizapp.data.QuestionnaireRepo

// dummy clas for implementation of the data
class QuestionnaireDataProviderImpl : QuestionnaireRepo {

    override  fun getQuestionnaireData(): Questions {
        return Questions( "success", listOf(
            Question("What color is the Sky?",  listOf("pink", "blue",  "green", "purple"), "blue"),
            Question("What color is the Grass?",  listOf("blue", "yellow",  "green", "white"), "green"),
            Question("What color are the Clouds?",  listOf("white", "yellow",  "green", "black"), "white"),
            Question("What color is the Sun?",  listOf("blue", "yellow",  "green", "purple"), "yellow"),
            Question("Who is our President?",  listOf("biden", "barack",  "clinton", "kennedy"), "biden"),
            Question("What does MVVM stand for?",  listOf("model-view-viewmodel", "model-view-controller",  "model-very-view-model", "model-view-intent"), "model-view-viewmodel"),
            Question("What is another name for WIFI?",  listOf("IEEE 802.11", "WLAN",  "Wireless Interner", "WPA"), "IEEE 802.11"),
        ))
    }
}