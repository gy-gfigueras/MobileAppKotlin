package org.gfigueras.practicaobligariauniversos.model.DAOUsers
import org.gfigueras.practicaobligariauniversos.model.entities.Universo

/**
 * Interfaz IDAOUsers
 * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
 */
interface IDAOUsers {
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