package com.example.desafioradix.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

class DetalhesDataResponse(

    val id : Int?,

    val name : String?,

    @SerializedName("description")
    val descricao : String?,

    val language : String?,

    @SerializedName("created_at")
    val dataCriacao : Date?,

    @SerializedName("stargazers_count")
    val numEstrelas : Int?,

    @SerializedName("forks_count")
    val numForks : Int?,

    @SerializedName("html_url")
    val url : String?,

    @SerializedName("open_issues_count")
    val issuesAbertas : Int?
)