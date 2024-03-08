package com.example.quizapp.data

import com.example.quizapp.domain.models.Questions

class QuestionnaireRepository(private val localDataRepo: QuestionnaireRepo) {

    fun getQuestionnaireData():Questions{
            return localDataRepo.getQuestionnaireData();
        }

}