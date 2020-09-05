package com.example.desafioradix.api.model

import com.google.gson.annotations.SerializedName

class RepositorioDataResponse(
    val id : Int?,

    @SerializedName("name")
    val nome : String?,

    @SerializedName("owner")
    val infoUsuario : UserDataResponse?,

    @SerializedName("description")
    val descricao : String?
)