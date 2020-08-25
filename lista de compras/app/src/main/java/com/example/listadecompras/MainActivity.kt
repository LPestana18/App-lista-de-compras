package com.example.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
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

            // Verificando se o usuário digitou algum valor
            if(produto.isNotEmpty()){
                // Enviado o item para a lista
                produtosAdapter.add(produto)

                // Limpando a caixa de text
                txt_produto.text.clear()

            }else {
                txt_produto.error = "Preencha um valor"
            }
        }

        list_view_produtos.setOnItemLongClickListener{
            adapterView: AdapterView<*>?, view: View, i: Int, l: Long ->

            // buscando o item clicado
            val item = produtosAdapter.getItem(i)

            // removendo o time clicado da lista
            produtosAdapter.remove(item)

            // Retorno indicando que o click foi realizado com sucesso
            true
        }
    }
}