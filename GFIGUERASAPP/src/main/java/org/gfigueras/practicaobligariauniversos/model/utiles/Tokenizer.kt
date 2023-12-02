package org.gfigueras.practicaobligariauniversos.model.utiles

import android.annotation.SuppressLint
import android.content.Context
import org.gfigueras.practicaobligariauniversos.controller.Controller
import org.gfigueras.practicaobligariauniversos.controller.IController
import org.gfigueras.practicaobligariauniversos.model.entities.User

/**
 * Singleton de un objeto tokenizer
 * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
 */
@SuppressLint("StaticFieldLeak")
object Tokenizer {
    private lateinit var context: Context
    private lateinit var controlador: IController

    /**
     * Funcion de inizializar, Setea el Controlador, y le pasa el contexto como parametro
     * @param context Contexto de la aplicacion, por ejemplo @this::LoginActivity...
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun initialize(context: Context) {
        this.context = context
        this.controlador = Controller(context)
    }

    // Función para tokenizar con acceso al contexto
    /**
     * Recibe un usuario en forma de String como parametro, y lo tokeniza separandolo por las ",", de forma que devuelve un array y retorna un Usuario, Si el universoFav es -1 o diferente de 1-10, lo setea como null
     * @param text Usuario en forma de String: "gfigueras,gfigueras@gmail.com,3"
     * return Retorna un objeto User
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
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

    /**
     * Tokenizar usuarios, recibe varias lineas con usuarios de la misma forma que en Tokenizar separa por "," & "\n", y retorna una lista de usuarios ordenada y sin meter al ADMIN
     * @param text Usuarios separados por "," y saltos de linea
     * @return retorna una Lista de Users, ordenada alfabeticamente y sin el usuario ADMIN
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
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
