package com.example.semesterproject1.models

data class GetBudgetResponse(
    val error: Boolean,
    val message: String,
    val userBudget: UserBudget
)