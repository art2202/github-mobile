package com.example.desafioradix.ui

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.desafioradix.Constantes
import com.example.desafioradix.MyApplication
import com.example.desafioradix.R
import com.example.desafioradix.api.model.DetalhesDataResponse
import com.example.desafioradix.common.Response
import com.example.desafioradix.common.Status
import com.example.desafioradix.ui.viewModel.DetalhesViewModel
import kotlinx.android.synthetic.main.activity_detalhes.*
import kotlinx.android.synthetic.main.activity_detalhes.botao_voltar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetalhesActivity : AppCompatActivity() {

    private val viewModel : DetalhesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)
        botao_voltar.setOnClickListener { onBackPressed() }
        viewModel.response().observe(this, Observer { response -> processResponse(response) })
        progress_bar.visibility = View.VISIBLE
        getDetalhes(intent.getStringExtra("nome_usuario")!!,
            intent.getIntExtra("repositorio_id", 0),
            intent.getStringExtra("nome_repositorio")!!
        )

    }

    private fun getDetalhes(nomeUsuario: String, repositorioId: Int, nomeRepositorio : String) {

        if (MyApplication.temInternet)
            viewModel.getDetalhes(nomeUsuario, nomeRepositorio)
        else
            viewModel.getDetalhesBD(repositorioId)

    }

    private fun processResponse(response: Response){
        when (response.status) {
            Status.SUCCESS -> responseSuccess(response.data)
            Status.ERROR -> responseFailure(response.error)
            else -> throw Exception("processResponseDetalhes error")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun responseSuccess(result: Any?){
        result as DetalhesDataResponse
        val sdf = SimpleDateFormat(Constantes.FORMATO_DATA, Locale.US)
        nome_repositorio.text = result.name
        contador_star.text = result.numEstrelas.toString()
        contador_fork.text = result.numForks.toString()
        descricao.text = result.descricao
        linguagem.text = result.language
        data.text = sdf.format(result.dataCriacao)
        issues.text = "Issues: ${result.issuesAbertas}"

        progress_bar.visibility = View.INVISIBLE
        abrir_git.setOnClickListener {
            abrirChrome(result.url)
        }
    }

    private fun responseFailure(erro : Throwable?){
        if(erro?.message == "Sem internet"){
            viewModel.getDetalhesBD(
                intent.getIntExtra("repositorio_id", 0)
            )
        }
        else{
            Toast.makeText(this, erro?.message, Toast.LENGTH_SHORT).show()
            progress_bar.visibility = View.INVISIBLE
        }

    }

    private fun abrirChrome(url : String?){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage("com.android.chrome")
        try {
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            intent.setPackage(null)
            startActivity(intent)
        }
    }
}
