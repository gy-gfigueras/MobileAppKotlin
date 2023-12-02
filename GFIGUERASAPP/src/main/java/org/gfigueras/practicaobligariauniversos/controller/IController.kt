package org.gfigueras.practicaobligariauniversos.controller

import org.gfigueras.practicaobligariauniversos.model.entities.Mapa
import org.gfigueras.practicaobligariauniversos.model.entities.Universo

interface IController {
    /**
     * Lista universos
     * @return Retorna una lista de Universos
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun listUniversos(): MutableList<Universo>

    /**
     * getUniverso, Recibe un universo por codigo y lo busca, puede retornar null
     * @return retona un Objeto Universo || null
     * @param codigo de universo, del 0 al 10
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getUniverso(cod:Int): Universo?

    /**
     * getUniverso, Recibe un universo por nombre y lo busca, puede retornar null
     * @return retorna Objeto Universo || null
     * @param name Nombre del universo:String
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getUniverso(name:String): Universo?

    /**
     * listarMapas, lista todos los mapas
     * @return Retorna una lista de mapas
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun listMapas(): MutableList<Mapa>
    /**
     * getUniverso, Recibe un Mapa por codigo y lo busca, puede retornar null
     * @return retona un Objeto Mapa || null
     * @param codigo de Mapa
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getMapa(cod:Int): Mapa?
    /**
     * getUniverso, Recibe un Mapa por nombre y lo busca, puede retornar null
     * @return retorna Objeto Mapa || null
     * @param name Nombre del Mapa:String
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getMapa(name:String): Mapa?

    /**
     * Retorna todos los mapas de un universo
     * @return Lista de Mapas || null
     * @param mundo Nombre del Universo
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getMapas(mundo:String):MutableList<Mapa>?
    /**
     * Retorna todos los mapas de un universo
     * @return Lista de Mapas || null
     * @param mundo Codigo del Universo
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getMapas(mundo: Int): MutableList<Mapa>?
    /**
     * Hace un inicio de sesion
     * @return Retorna true o false si la contraseña y el usuario son correctos
     * @param username
     * @param password
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    suspend fun login(username:String, password:String):Boolean
    /**
     * Registra un usuario
     * @return Retorna un numero segun el resultado del estado del Registro
     * @param username
     * @param email
     * @param password
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    suspend fun signUp(username:String, email:String, password:String):Int

    /**
     * Retorna un Usuario segun un nombre y contraseña
     * @param username
     * @param password
     * @return Retorna el usuario en toString()
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    suspend fun getUser(username: String, password: String): String

    /**
     * Cambia la contraseña
     * @param username
     * @param password
     * @param passwordNew Contraseña nueva
     * @return Retorna un Int depende de el resultado:
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    suspend fun changePassword(username:String, password: String, passwordNew: String):Int

    /**
     * Settea Universo favorito a un User por Username
     * @param username
     * @param universo
     * @return Retorna true o false
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    suspend fun setUniverseFav(username: String,universo: Universo?):Boolean

    /**
     * Borrar usuario
     * @param username DEBE DE SER ADMIN
     * @param password DEBE DE SER LA DE ADMIN
     * @param usernameToDelete Usuario a borrar
     * @return retorna true o false
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    suspend fun deleteUser(username:String, password: String, usernameToDelete:String):Boolean
    /**
     * Retorna todos los usuarios
     * @return retorna usuarios separados en lineas en forma de String
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    suspend fun getUsers():String?

    /**
     * Cambia contraseña si se olvida
     * @param username
     * @param email
     * @param password
     * @return Retorna un integer segun el resultado: <br>0: Correcto,<br>-1: Incorrecto posible fallo de correo o nombre, <br>-2: SQLException
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    suspend fun changePasswordForgotten(username:String, email:String, password:String):Int
}