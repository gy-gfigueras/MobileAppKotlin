package org.gfigueras.practicaobligariauniversos.model.utiles

import android.annotation.SuppressLint
import android.content.Context
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController
import org.gfigueras.practicaobligariauniversos.model.entities.User

@SuppressLint("StaticFieldLeak")
object Tokenizer {
    private lateinit var context: Context
    private lateinit var controlador: IController

    fun initialize(context: Context) {
        this.context = context
        this.controlador = Controller(context)
    }

    // Función para tokenizar con acceso al contexto

    fun tokenizar(text: String): User {
        val parametros = text.split(",")
        if (parametros.get(3).toInt() == -1) {
            return User(parametros[0], parametros[1], parametros[2], null)
        } else {
            return User(
                parametros[0],
                parametros[1],
                parametros[2],
                controlador.getUniverso(parametros.get(3).toInt())
            )

        }
    }


    fun tokenizar2(text: String): User {
        val parametros = text.split(",")
        return if (parametros[3].toInt() == -1) {
            User(parametros[0], parametros[1], parametros[2], null)
        } else {
            User(
                parametros[0],
                parametros[1],
                parametros[2],
                controlador.getUniverso(parametros[3].toInt())
            )
        }
    }

    // Método tokenizarUsers para convertir un bloque de texto en una lista de usuarios
    fun tokenizarUsers(text: String): MutableList<User> {
        val lineas = text.split("\n")
        val lista: MutableList<User> = mutableListOf()

        lineas.forEach { linea ->
            val campos = linea.split(",")
            if (campos.size == 4) {
                lista.add(User(campos[0], campos[1], campos[2], null))
            } else {
                // Agregar un usuario con valores predeterminados si la línea no tiene la cantidad correcta de campos
                lista.add(User("none", "none@gmail.com", "User", null))
            }
        }
        lista.removeIf{ user -> user.getRole() == "ADMIN" }
        lista.sortWith(compareBy({ it.getUsername() != "none" }, { it.getUsername() }))

        return lista
    }


}
