package com.example.desafioradix.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioradix.MyApplication
import com.example.desafioradix.R
import com.example.desafioradix.adapter.RepositorioAdapter
import com.example.desafioradix.api.model.DetalhesDataResponse
import com.example.desafioradix.api.model.RepositorioDataResponse
import com.example.desafioradix.common.Response
import com.example.desafioradix.common.Status
import com.example.desafioradix.ui.viewModel.InformacoesViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_informacoes.*
import org.koin.androidx.viewmodel.ext.android.viewModel


@Suppress("UNCHECKED_CAST")
class InformacoesActivity : AppCompatActivity() {

    private val viewModel : InformacoesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacoes)
        shimmer.startShimmer()
        shimmer.visibility = View.VISIBLE
        viewModel.response().observe(this, Observer { response -> processResponse(response) })

//        val nome = intent.getStringExtra("nomeUsuario")!!
        val nome = "art2202"
        println("onCreate")
        nome_usuario.text = nome
        getRepositorios(nome)
        botao_voltar.setOnClickListener { onBackPressed() }
    }

    private fun getRepositorios(nome : String){
        if(MyApplication.temInternet) {
            println(MyApplication.temInternet)
            viewModel.getRepositorios(nome)
        }
        else{
            println(MyApplication.temInternet)
            viewModel.getRepositoriosBD(nome)
        }
    }

    private fun processResponse(response: Response){
        when (response.status) {
            Status.SUCCESS -> responseSuccess(response.data)
            Status.ERROR -> responseFailure(response.error)
            else -> throw Exception("processResponseInformacoes error")
        }
    }

    private fun responseSuccess(result: Any?) {
        result as List<RepositorioDataResponse>
        val nome = intent.getStringExtra("nomeUsuario") ?: throw Exception("Nome não recebido na intent")

        val picasso = Picasso.get()

        picasso.load(result[0].infoUsuario?.avatarUrl).into(foto_usuario)

        if(result.isEmpty()){
            Toast.makeText(this, "Sem dados locais", Toast.LENGTH_SHORT).show()
        }
        if(!MyApplication.temInternet){
            recycler_repositorios.adapter = RepositorioAdapter(nome, listOf(), this)
        }
        recycler_repositorios.adapter = RepositorioAdapter(nome,result, this)
        recycler_repositorios.layoutManager = LinearLayoutManager(this)
        recycler_repositorios.isNestedScrollingEnabled = true
        shimmer.stopShimmer()
        shimmer.visibility = View.INVISIBLE
    }

    private fun responseFailure(erro: Throwable?){
        val nome = intent.getStringExtra("nomeUsuario")!!


        if(erro?.message == "Sem internet")
            viewModel.getRepositoriosBD(nome)


        Toast.makeText(this, "erro ao fazer requisição", Toast.LENGTH_SHORT).show()
        shimmer.stopShimmer()
        shimmer.visibility = View.INVISIBLE

    }
}
