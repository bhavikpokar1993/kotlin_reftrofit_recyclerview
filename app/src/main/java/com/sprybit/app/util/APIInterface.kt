package com.sprybit.app.util


import com.sprybit.app.Quote
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

internal interface APIInterface {

    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST("call_api.php")
    fun getAllQuote(@retrofit2.http.Field("date") date: String, @retrofit2.http.Field("rquest") req: String): retrofit2.Call<List<Quote>>

}
