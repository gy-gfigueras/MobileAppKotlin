package org.gfigueras.practicaobligariauniversos.model.utiles

import android.content.Context
import android.util.Log
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController
import org.gfigueras.practicaobligariauniversos.model.entities.User

object Tokenizer {
    private lateinit var context: Context

    fun initialize(context: Context) {
        this.context = context
    }

    // Función para tokenizar con acceso al contexto
    fun tokenizar(text: String): User {
        val parametros = text.split(",")
        val controlador: IController = Controller(context)
        if(parametros.get(3).toInt() == -1){
            return User(parametros[0], parametros[1], parametros[2], null)
        }else{
            return User(parametros[0], parametros[1], parametros[2], controlador.getUniverso(parametros.get(3).toInt()))

        }
    }
}
