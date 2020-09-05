package com.example.desafioradix.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafioradix.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        botao_buscar.setOnClickListener { enviar() }
    }

    fun enviar(){
        val nomeUsuario = edit_text.text.toString()

        if(nomeUsuario.isNotEmpty()){
            val intent = Intent(applicationContext, InformacoesActivity::class.java)
            intent.putExtra("nomeUsuario", nomeUsuario)
            startActivity(intent)
        }
        else{
            input_layout.error = "Insira o nome do usuário."
        }
    }
}
