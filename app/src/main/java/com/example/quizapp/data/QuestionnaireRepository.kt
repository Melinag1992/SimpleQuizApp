package com.example.quizapp.data

import com.example.quizapp.domain.models.Questions

class QuestionnaireRepository(private val dummyApi: QuestionnaireDummyApi) {

    fun getQuestionnaireData():Questions{
            return dummyApi.getQuestionnaireData();
        }

}