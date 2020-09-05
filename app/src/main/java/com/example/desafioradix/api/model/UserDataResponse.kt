package com.example.desafioradix.api.model

import com.google.gson.annotations.SerializedName

class UserDataResponse(
    val login : String?,
    @SerializedName("avatar_url")
    val avatarUrl : String?
)