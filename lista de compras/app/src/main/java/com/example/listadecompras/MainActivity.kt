package com.example.listadecompras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onResume() {

        super.onResume()
        val adapter = list_view_produtos.adapter as ProdutoAdapter

        adapter.clear()
        adapter.addAll(produtoGlobal)

        val soma = produtoGlobal.sumByDouble { it.valor * it.quantidade }

        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        txt_total.text = "TOTAL: ${f.format(soma)}"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Implementação do adaptador
        val produtosAdapter = ProdutoAdapter(this)
        produtosAdapter.addAll(produtoGlobal)

        // Definindo o adaptador na lista
        list_view_produtos.adapter = produtosAdapter

        // Definição do ouvinte do botão

        btn_adicionar.setOnClickListener {

            // criando a Intent explícita
            val intent = Intent(this, CadastroActivity::class.java)

            // iniciando a atividade
            startActivity(intent)

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