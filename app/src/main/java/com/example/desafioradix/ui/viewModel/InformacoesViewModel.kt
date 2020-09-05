package com.example.desafioradix.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioradix.api.repository.InformacoesRepository
import com.example.desafioradix.common.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InformacoesViewModel(private val repository : InformacoesRepository) : ViewModel(){

    private val response = MutableLiveData<Response>()

    fun getRepositorios(nomeUsuario : String){

        CoroutineScope(Dispatchers.IO).launch {
            try{
                val requisicao = repository.getRepositorios(nomeUsuario)
                val repositorios = repository.mapRepositorios(requisicao)

                repository.adicionaBD(nomeUsuario, repositorios)
                response.postValue(Response.success(repositorios))
            }

            catch (t : Throwable){
                println(t.message + "  getRepositorios viewmodel  ")
                response.postValue(Response.error(Throwable("Sem internet")))
            }
        }
    }

    fun getRepositoriosBD(nomeUsuario: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val informacoes = repository.getInformacoesOffline(nomeUsuario)
                if(informacoes.isEmpty()){

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


    fun response() : MutableLiveData<Response>{
        return response
    }

}