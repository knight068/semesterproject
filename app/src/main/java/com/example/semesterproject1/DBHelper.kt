package com.example.semesterproject1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.service.autofill.UserData
import androidx.core.content.contentValuesOf

class DBHelper(context: Context):SQLiteOpenHelper(context,"UserData",null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table UserData(userEmail TEXT primary key, Password TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        p0?.execSQL("drop table if exists UserData ")
    }
    fun insertData(userEmail:String,password:String): Boolean {
        val p0 =this.writableDatabase
        val cv = ContentValues()
        cv.put("userEmail",userEmail)
        cv.put("password",password)
        val result =p0.insert("UserData",null,cv)
        if (result== -1 .toLong()){
            return false
        }
        return true
    }
    fun checkUserPassword(userEmail:String,password: String): Boolean {
        val p0 =this.writableDatabase
        val query ="select * from UserData where userEmail ='$userEmail' and password ='$password'"
        val cursor = p0.rawQuery(query,null)
        if (cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }
}