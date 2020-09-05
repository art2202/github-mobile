package com.example.desafioradix.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioradix.R
import com.example.desafioradix.api.model.RepositorioDataResponse
import com.example.desafioradix.ui.DetalhesActivity
import kotlinx.android.synthetic.main.item_repositorio.view.*

class RepositorioAdapter(private val nomeUsuario : String, private val repositorios : List<RepositorioDataResponse> , val context: Context) : RecyclerView.Adapter<RepositorioAdapter.MyViewHolder>(){

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_repositorio, parent, false))
    }

    override fun getItemCount(): Int = repositorios.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = repositorios.get(position)

        holder.itemView.nome_repositorio.text = item.nome
        holder.itemView.text_descricao.text = item.descricao
        holder.itemView.botao_ver_repositorio.setOnClickListener {
            val intent = Intent(context, DetalhesActivity::class.java)
            intent.putExtra("nome_usuario", nomeUsuario)
            intent.putExtra("repositorio_id", item.id)
            intent.putExtra("nome_repositorio", item.nome)
            context.startActivity(intent)
        }

    }

}