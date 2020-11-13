package com.example.vviped.model

import retrofit2.Call
import retrofit2.http.GET

interface CampaignService {

    @GET("api.php?apicall=campaigns")
    fun getCampaigns(): Call<List<CampaignModel>>


}
