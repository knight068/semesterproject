package com.example.semesterproject1.api

import com.example.semesterproject1.models.BudgetUpdateResponse
import com.example.semesterproject1.models.DefaultResponse
import com.example.semesterproject1.models.GetBudgetResponse
import com.example.semesterproject1.models.LogInResponse
import com.example.semesterproject1.models.NetBillsResponse
import com.example.semesterproject1.models.RemindersAddResponse
import com.example.semesterproject1.models.RemindersResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.Date

interface Api {
    @FormUrlEncoded
    @POST("api/register")
    fun createUser(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("city") city:String

    ):Call<DefaultResponse>

    @FormUrlEncoded
    @POST("api/login")
    fun logInUser(
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<LogInResponse>

    @FormUrlEncoded
    @POST("api/updateBudgetBill/{userId}")
    fun updateBudget(
        @Path("userId")userId:String,
        @Field("padgetval")padgetval:Float,
        @Field("padgDate1")padgDate1:String,
        @Field("padgDate2")padgDate2:String
    ):Call<BudgetUpdateResponse>



    @GET("api/getNetbills/{userId}")
    fun getBills(
        @Path("userId")userId:String
    ):Call<NetBillsResponse>

    @GET("api/getUserBudget/{userId}")
    fun getBudget(
        @Path("userId")userId:String
    ):Call<GetBudgetResponse>

    @GET("/api/getReminder/{userId}")
    fun getReminders(
        @Path("userId")userId:String
    ):Call<RemindersResponse>

    @FormUrlEncoded
    @POST("/api/AddReminder/{userId}")
    fun postReminders(
        @Path("userId")userId:String,
        @Field("RemVal")RemVal:String,
        @Field("RemDate")RemDate:String
    ):Call<RemindersAddResponse>
}