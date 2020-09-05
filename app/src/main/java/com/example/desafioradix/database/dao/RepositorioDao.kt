package com.example.desafioradix.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.desafioradix.database.entity.RepositorioEntity

@Dao
interface RepositorioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(repositorio : List<RepositorioEntity>)

    @Query("SELECT * FROM repositorio WHERE nome_usuario = :nomeUsuario")
    fun getRepositorios(nomeUsuario : String): List<RepositorioEntity>
}