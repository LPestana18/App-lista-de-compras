package com.example.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Implementação do adaptador
        val produtosAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        // Definindo o adaptador na lista
        list_view_produtos.adapter = produtosAdapter

        // Definição do ouvinte do botão
        btn_inserir.setOnClickListener{

            // Pegando o valor digitado pelo usuário
            val produto = txt_produto.text.toString()

            // Enviado o item para a lista
            produtosAdapter.add(produto)

        }
    }
}