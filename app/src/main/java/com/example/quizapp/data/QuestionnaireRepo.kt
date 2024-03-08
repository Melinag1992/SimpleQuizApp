package com.example.quizapp.data

import com.example.quizapp.domain.models.Questions

interface QuestionnaireRepo {
    fun getQuestionnaireData (): Questions

}