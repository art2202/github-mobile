package com.example.desafioradix.api.repository

import com.example.desafioradix.api.RestApi
import com.example.desafioradix.api.model.RepositorioDataResponse
import com.example.desafioradix.api.model.UserDataResponse
import com.example.desafioradix.database.AppDatabase
import com.example.desafioradix.database.entity.RepositorioEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class InformacoesRepository(private val restApi: RestApi) {

    private val informacoesDao  = AppDatabase.getDatabase()!!.repositorioDao()
    suspend fun getRepositorios(nomeUsuario : String): Response<List<RepositorioDataResponse>> {

        try{
            val requisicao = restApi.getApiService().getRepositorios("users/$nomeUsuario/repos")

            if(!requisicao.isSuccessful){
                throw Exception("Erro ao fazer requisição dos repositorios")
            }
            else
                return requisicao

        }
        catch (e : Exception){
            println(e.message + "info repository")
            throw Exception(e.message)
        }
    }

    fun getInformacoesOffline(nomeUsuario: String): List<RepositorioDataResponse>{
        val repositorios = informacoesDao.getRepositorios(nomeUsuario)

//        CoroutineScope(Dispatchers.IO).launch {  }
        return repositorios.map {
            RepositorioDataResponse(
                it.id,
                it.nome,
                UserDataResponse(
                    it.nome,
                    it.imageUrl
                ),
                it.descricao
            )
        }
    }

    fun adicionaBD(nome : String, requisicao : List<RepositorioDataResponse>){
        informacoesDao.add(requisicao.map{
            RepositorioEntity(
                it.id!!,
                it.nome!!,
                it.descricao,
                nome,
                it.infoUsuario?.avatarUrl!!
            )
        })
    }

    fun mapRepositorios(requisicao : Response<List<RepositorioDataResponse>>) : List<RepositorioDataResponse>{

        return requisicao.body()!!.map {
            RepositorioDataResponse(it.id,it.nome, it.infoUsuario, it.descricao)
        }
    }


}