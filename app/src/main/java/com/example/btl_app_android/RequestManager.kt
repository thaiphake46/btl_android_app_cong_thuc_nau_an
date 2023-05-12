package com.example.btl_app_android

import android.content.Context
import com.example.btl_app_android.listeners.DetailsRecipeListener
import com.example.btl_app_android.listeners.InstructionsListener
import com.example.btl_app_android.listeners.RandomRecipeResponseListener
import com.example.btl_app_android.listeners.SimilarRecipeListener
import com.example.btl_app_android.models.InstructionsRecipeResponse

import com.example.btl_app_android.models.RandomRecipeApiResponse
import com.example.btl_app_android.models.RecipeDetailsResponse
import com.example.btl_app_android.models.SimilarRecipesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class RequestManager(private var context: Context) {
    private var urlApi = "https://api.spoonacular.com/"
    private var retrofit =
        Retrofit.Builder().baseUrl(urlApi).addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getRandomRecipe(listener: RandomRecipeResponseListener, tags: String) {
        val apiKey = context.getString(R.string.api_key)
        val number = "10"

        val callRanDomRecipes: CallRanDomRecipes = retrofit.create(CallRanDomRecipes::class.java)
        val call: Call<RandomRecipeApiResponse> =
            callRanDomRecipes.callRanDomRecipe(apiKey, number, tags)
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

    fun getDetailsRecipe(listener: DetailsRecipeListener, id: String) {
        val apiKey = context.getString(R.string.api_key)

        val callRecipeDetails: CallRecipeDetails = retrofit.create(CallRecipeDetails::class.java)
        val call: Call<RecipeDetailsResponse> = callRecipeDetails.callRecipeDetails(id, apiKey)
        call.enqueue(object : Callback<RecipeDetailsResponse> {
            override fun onResponse(
                call: Call<RecipeDetailsResponse>,
                response: Response<RecipeDetailsResponse>
            ) {
                if (!response.isSuccessful) {
                    listener.didError(response.message())
                    return
                }
                listener.didFetch(response.body(), response.message())
            }

            override fun onFailure(call: Call<RecipeDetailsResponse>, t: Throwable) {
                listener.didError(t.message)
            }
        })
    }

    fun getSimilarRecipes(listener: SimilarRecipeListener, id: String) {
        val apiKey = context.getString(R.string.api_key)
        val number: String = "4"
        val callSimilarRecipes: CallSimilarRecipes = retrofit.create(CallSimilarRecipes::class.java)
        val call: Call<List<SimilarRecipesResponse>> =
            callSimilarRecipes.callSimilarRecipes(id, number, apiKey)
        call.enqueue(object : Callback<List<SimilarRecipesResponse>> {
            override fun onResponse(
                call: Call<List<SimilarRecipesResponse>>,
                response: Response<List<SimilarRecipesResponse>>
            ) {
                if (!response.isSuccessful) {
                    listener.didError(response.message())
                    return
                }
                listener.didFetch(response.body(), response.message())
            }

            override fun onFailure(call: Call<List<SimilarRecipesResponse>>, t: Throwable) {
                listener.didError((t.message))
            }
        })
    }

    fun getInstructions(listener: InstructionsListener, id: String) {
        val apiKey = context.getString(R.string.api_key)
        val callInstructions: CallInstructions = retrofit.create(CallInstructions::class.java)
        val call: Call<List<InstructionsRecipeResponse>> =
            callInstructions.callInstructions(id, apiKey)
        call.enqueue(object : Callback<List<InstructionsRecipeResponse>> {
            override fun onResponse(
                call: Call<List<InstructionsRecipeResponse>>,
                response: Response<List<InstructionsRecipeResponse>>
            ) {
                if (!response.isSuccessful) {
                    listener.didError(response.message())
                }
                listener.didFetch(response.body(), response.message())
            }

            override fun onFailure(call: Call<List<InstructionsRecipeResponse>>, t: Throwable) {
                listener.didError(t.message)
            }

        })
    }

    private interface CallRecipeDetails {
        @GET("recipes/{id}/information")
        fun callRecipeDetails(
            @Path("id") id: String,
            @Query("apiKey") apiKey: String,
        ): Call<RecipeDetailsResponse>
    }

    private interface CallRanDomRecipes {
        @GET("recipes/random")
        fun callRanDomRecipe(
            @Query("apiKey") apiKey: String,
            @Query("number") number: String,
            @Query("tags") tags: String
        ): Call<RandomRecipeApiResponse>
    }

    private interface CallSimilarRecipes {
        @GET("recipes/{id}/similar")
        fun callSimilarRecipes(
            @Path("id") id: String,
            @Query("number") number: String,
            @Query("apiKey") apiKey: String
        ): Call<List<SimilarRecipesResponse>>
    }

    private interface CallInstructions {
        @GET("recipes/{id}/analyzedInstructions")
        fun callInstructions(
            @Path("id") id: String,
            @Query("apiKey") apiKey: String
        ): Call<List<InstructionsRecipeResponse>>
    }
}
