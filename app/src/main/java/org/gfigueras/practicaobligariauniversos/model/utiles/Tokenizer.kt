package org.gfigueras.practicaobligariauniversos.model.utiles

import org.gfigueras.practicaobligariauniversos.model.entities.User

object Tokenizer {
    public fun tokenizar(text:String): User {
        val parametros = text.split(",")
        return User(parametros[0], parametros[1], parametros[2])
    }
}