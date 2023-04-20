package com.example.btl_app_android

import android.content.Context
import com.example.btl_app_android.listeners.RandomRecipeResponseListener

import com.example.btl_app_android.models.RandomRecipeApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RequestManager(private var context: Context) {
    private var urlApi = "https://api.spoonacular.com/"
    private var retrofit =
        Retrofit.Builder().baseUrl(urlApi).addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getRandomRecipe(listener: RandomRecipeResponseListener) {
        val apiKey = context.getString(R.string.api_key)
        val number = "10"

        val callRanDomRecipes: CallRanDomRecipes = retrofit.create(CallRanDomRecipes::class.java)
        val call: Call<RandomRecipeApiResponse> = callRanDomRecipes.callRanDomRecipe(apiKey, number)
        call.enqueue(object : Callback<RandomRecipeApiResponse> {
            override fun onResponse(
                call: Call<RandomRecipeApiResponse>,
                response: Response<RandomRecipeApiResponse>
            ) {
                if (!response.isSuccessful) {
                    listener.didError(response.message())
                    return
                }
                listener.didFetch(response.body(), response.message())
            }

            override fun onFailure(call: Call<RandomRecipeApiResponse>, t: Throwable) {
                listener.didError(t.message)
            }
        })
    }

    private interface CallRanDomRecipes {
        @GET("recipes/random")
        fun callRanDomRecipe(
<<<<<<< HEAD
            @Query("apiKey") apiKey: String, @Query("number") number: String
=======
            @Query("apiKey") apiKey: String,
            @Query("number") number: String
>>>>>>> 0ed9da0bad01169526f61ccbca536d58be8a8506
        ): Call<RandomRecipeApiResponse>
    }
}
