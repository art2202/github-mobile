package com.example.desafioradix.api.repository

import com.example.desafioradix.Constantes
import com.example.desafioradix.api.RestApi
import com.example.desafioradix.api.model.DetalhesDataResponse
import com.example.desafioradix.database.AppDatabase
import com.example.desafioradix.database.entity.DetalheRepositorioEntity
import java.text.SimpleDateFormat
import java.util.*

class DetalhesRepository(private val restApi: RestApi) {

    private val detalhesDao = AppDatabase.getDatabase()!!.detalhesRepositorioDao()
    suspend fun getDetalhes(nomeUsuario : String, nomeRepositorio : String): DetalhesDataResponse? {

        try{
            val requisicao = restApi.getApiService().getDetalhes("repos/$nomeUsuario/$nomeRepositorio")

            if(!requisicao.isSuccessful){
                throw Exception("Erro ao fazer requisição dos repositorios")
            }
            else
                return requisicao.body()

        }
        catch (e : Exception){
            println(e.message + "detalhes repository")
            throw Exception(e.message)
        }
    }

    fun getDetalhesOffline(repositorioId: Int): DetalhesDataResponse? {
        val detalheRepositorioEntity = detalhesDao.getDetalhes(repositorioId)

        val sdf = SimpleDateFormat(Constantes.FORMATO_DATA, Locale.US)
        return DetalhesDataResponse(
            detalheRepositorioEntity.id,
            detalheRepositorioEntity.name,
            detalheRepositorioEntity.descricao,
            detalheRepositorioEntity.language,
            sdf.parse(detalheRepositorioEntity.dataCriacao!!),
            detalheRepositorioEntity.numEstrelas,
            detalheRepositorioEntity.numForks,
            detalheRepositorioEntity.url,
            detalheRepositorioEntity.issuesAbertas
        )
    }

    fun adicionaBD(requisicao: DetalhesDataResponse?) {
        val sdf = SimpleDateFormat(Constantes.FORMATO_DATA, Locale.US)

        detalhesDao.add(
            DetalheRepositorioEntity(
                requisicao?.id!!,
                requisicao.name!!,
                requisicao.descricao,
                requisicao.language,
                sdf.format(requisicao.dataCriacao!!),
                requisicao.numEstrelas,
                requisicao.numForks,
                requisicao.url,
                requisicao.issuesAbertas,
                requisicao.id
            )
        )
    }
}