package com.example.desafioradix.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioradix.api.repository.DetalhesRepository
import com.example.desafioradix.common.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetalhesViewModel(private val repository : DetalhesRepository) : ViewModel() {

    private val response = MutableLiveData<Response>()

    fun getDetalhes(
        nomeUsuario: String,
        nomeRepositorio: String
    ){

        CoroutineScope(Dispatchers.IO).launch {
            try{
                val requisicao = repository.getDetalhes(nomeUsuario, nomeRepositorio)
                repository.adicionaBD(requisicao)
                response.postValue(Response.success(requisicao))
            }

            catch (t : Throwable){
                println(t.message + "  getRepositorios viewmodel  ")
                response.postValue(Response.error(Throwable("Sem internet")))
            }
        }
    }

    fun getDetalhesBD(repositorioId : Int){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val informacoes = repository.getDetalhesOffline(repositorioId)
                if(informacoes == null){

                    response.postValue( (Response.error(Throwable("sem dados locais") ) ) )
                }
                else
                    response.postValue(Response.success(informacoes))
            }
            catch (t : Throwable){
                response.postValue((Response.error(t)))
            }

        }

    }

    fun response(): MutableLiveData<Response> {
        return response
    }
}