package com.example.desafioradix.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "detalhe_repositorio",
    foreignKeys = [
    ForeignKey(
        entity = RepositorioEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("repositorio_id")
    )]
)
data class DetalheRepositorioEntity(

    @PrimaryKey
    val id : Int,

    val name : String,

    val descricao : String?,

    val language : String?,

    @ColumnInfo(name = "data_criacao")
    val dataCriacao : String?,

    @ColumnInfo(name = "num_estrelas")
    val numEstrelas : Int?,

    @ColumnInfo(name = "num_forks")
    val numForks : Int?,

    val url : String?,

    @ColumnInfo(name = "issuas_abertas")
    val issuesAbertas : Int?,

    @ColumnInfo(name = "repositorio_id")
    val repositorioId : Int

)