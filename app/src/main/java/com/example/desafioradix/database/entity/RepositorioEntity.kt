package com.example.desafioradix.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(
    tableName = "repositorio"
)
data class RepositorioEntity(

    @PrimaryKey
    val id : Int,

    val nome : String,

    val descricao : String?,

    @ColumnInfo(name = "nome_usuario")
    val usuarioNome : String,

    @ColumnInfo(name = "image_url")
    val imageUrl : String
)