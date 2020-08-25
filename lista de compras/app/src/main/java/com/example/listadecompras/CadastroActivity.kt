package com.example.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)


        btn_inserir.setOnClickListener{

            // Pegando o valor digitado pelo usuário
            val produto = txt_produto.text.toString()

            // Verificando se o usuário digitou algum valor
            if(produto.isNotEmpty()){

                // Limpando a caixa de text
                txt_produto.text.clear()

            }else {
                txt_produto.error = "Preencha um valor"
            }
        }
    }
}