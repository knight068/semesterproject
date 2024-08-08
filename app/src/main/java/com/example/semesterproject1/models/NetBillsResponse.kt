package com.example.semesterproject1.models

import com.google.gson.annotations.SerializedName

data class NetBillsResponse(
    val data4: Data4,
    val error: Boolean,
    val message: String
)