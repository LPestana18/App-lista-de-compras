package com.example.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.listadecompras.database.database
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.db.*
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onResume() {
        super.onResume()

        val adapter = list_view_produtos.adapter as ProdutoAdapter

        database.use {
            //Efetuando uma consulta no banco de dados
            select("produtos").exec {

                //Criando o parser que montará o objeto produto
                val parser = rowParser { id: Int, nome: String,
                                         quantidade: Int,
                                         valor: Double,
                                         foto: ByteArray? ->
                    //Colunas do banco de dados

                    //Montagem do objeto Produto com as colunas do banco
                    Produto(id, nome, quantidade, valor, foto?.toBitmap())
                }

                //criando a lista de produtos com dados do banco
                var listaProdutos = parseList(parser)

                //limpando os dados da lista e carregando as novas informações
                adapter.clear()
                adapter.addAll(listaProdutos)

                //efetuando a multiplicação e soma da quantidade e valor
                val soma = listaProdutos.sumByDouble { it.valor * it.quantidade }

                //formatando em formato moeda
                val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
                txt_total.text = "TOTAL: ${f.format(soma)}"
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Implementação do adaptador

        val adapter2 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        val adapter = ProdutoAdapter(this)

        // Definindo o adaptador na lista
        list_view_produtos.adapter = adapter

        // Definição do ouvinte do botão

        btn_adicionar.setOnClickListener {
            startActivity<CadastroActivity>()

//            // criando a Intent explícita
//            val intent = Intent(this, CadastroActivity::class.java)
//
//            // iniciando a atividade
//            startActivity(intent)

        }

//        list_view_produtos.setOnItemClickListener { adapterView: AdapterView<*>, view: View?, i: Int, l: Long ->
//
//            alert("Mensagem", "Titulo") {
//                //botão OK
//                yesButton {
//                    //Ação caso escolheu a opção SIM
//                }
//
//                // Botão cancel
//
//                noButton {
//                    //Ação caso escolheu a opção NAO
//                }
//            }.show()
//        }

        //Definição do ouvinte da lista para clicks longos
        list_view_produtos.setOnItemLongClickListener { adapterView: AdapterView<*>?, view: View, i: Int, l: Long ->

            //SOLUÇÂO PARA ATUALIZAR UM REGISTRO

            val opcoes = listOf("editar", "excluir")

            val opc_editar = 0;
            val opc_excluir = 1;

            selector("O que deseja fazer?", opcoes) { dialogInterface, position ->

                when (position) {

                    opc_editar -> {
                        alert("Editar").show()
                        //toast("Editar)
                        val idItem = adapter.getItem(i)
                        val nome = adapter.getItem(i)

                        atualizarProduto(idItem!!.id, nome!!.nome)

                    }

                    opc_excluir -> {

                        //buscando o item clicado
                        val item = adapter.getItem(i)

                        //removendo o item clicado da lista
                        adapter.remove(item)

                        //delentando do banco de dados
                        deletarProduto(item!!.id)

                        toast("item deletado com sucesso")

                    }
                }

            }

            true
        }
    }

    fun deletarProduto(idProduto: Int) {

        database.use {
            delete("produtos", "id = {id}", "id" to idProduto)
        }
    }

    fun atualizarProduto(idProduto: Int, nome: String) {
        database.use {
            update("produtos", "nome" to nome).whereArgs("id = {id}", "id" to idProduto).exec()
        }
    }
}