package com.example.quizapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.domain.models.QuestionaireDataProvider
import com.example.quizapp.data.QuestionnaireRepository
import com.example.quizapp.domain.models.Question

class QuestionaireViewModel : ViewModel(){

    private val questionaireRepository = QuestionnaireRepository(QuestionaireDataProvider())
    // our list of questions to be asked
    private val questions = questionaireRepository.getQuestionnaireData().questions
    // our current question
    private val questionData = MutableLiveData<Question>()
    private val scoreData = MutableLiveData<String>()
    private val selectedAnswer = MutableLiveData<String>()
    private var isComplete = MutableLiveData<Boolean>();
    private var isReset = MutableLiveData<Boolean>();
    // to display our score at the end of quiz
    var totalCorrect =  0
    // starting with the first question
    var questionIndex = 0;

    init {
        setQuestionData()
    }

    fun getQuestionData():LiveData<Question> = questionData
    fun getTotalScore():LiveData<String> = scoreData
    fun getIsCompleted():LiveData<Boolean> = isComplete
    fun getIsReset():LiveData<Boolean> = isReset


    private fun setQuestionData(){
        if(!questions.isNullOrEmpty()){
         questionData.value = questions[questionIndex]
            scoreData.value = " Score : $totalCorrect / ${questions.size}"
        }
    }

    fun setSelectedAnswer(text: String) {
        selectedAnswer.value = text
    }

    fun setIsReset(){
        isReset.value = true
    }

    fun updateQuestion(){
        updateScore()
        // updates our question index
        questionIndex++
        // making sure our index doesn't go above the list size - no out of bounds
       if(questionIndex <= questions.size-1){
           questionData.value = questions[questionIndex]
        }else{
            // reached end of questions list
           isComplete.value = true
        }
    }

    fun resetValues() {
        questionIndex =0;
        totalCorrect =0;
        setQuestionData()
    }

    private fun updateScore (){
        // check if answer against correct answer
        if(selectedAnswer.value === questions[questionIndex].answer ){
            totalCorrect++
            scoreData.value = "Score : $totalCorrect / ${questions.size}"
        }
    }


}