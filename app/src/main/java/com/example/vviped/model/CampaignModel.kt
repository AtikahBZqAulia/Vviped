package com.example.vviped.model

data class CampaignModel (
    val id : Int,
    val path : String,
    val campaign_category : String,
    val campaign_title: String,
    val campaign_desc: String,
    val donation_goes: String,
    val usage_details : String,
    val phone_number : String
)