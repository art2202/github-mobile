package com.example.desafioradix.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.desafioradix.database.entity.DetalheRepositorioEntity

@Dao
interface DetalhesRepositorioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(detalheRepositorioEntity: DetalheRepositorioEntity)

    @Query("SELECT * FROM detalhe_repositorio WHERE repositorio_id = :repositorioId")
    fun getDetalhes(repositorioId : Int) : DetalheRepositorioEntity
}