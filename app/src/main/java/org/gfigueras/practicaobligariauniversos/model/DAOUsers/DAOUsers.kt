package org.gfigueras.practicaobligariauniversos.model.DAOUsers

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.gfigueras.practicaobligariauniversos.model.entities.Universo

class DAOUsers : IDAOUsers {
    private val client = HttpClient()

    private val URL: String = "https://gfaccounts-grtx.1.ie-1.fl0.io/"

    override suspend fun login(username: String, password: String): Boolean {
        val url = "$URL/login/$username/$password"
        val client = HttpClient()

        try {
            return client.get<String>(url).toBoolean()
        } catch (e: Exception) {
            return false
        } finally {
            client.close()
        }
    }

    override suspend fun signUp(username: String, email: String, password: String): Int {
        val url = "$URL/signup/$username/$email/$password"
        val client = HttpClient()
        try {
            return client.get<String>(url).toInt()

        } catch (e: Exception) {
            return -1
        } finally {
            client.close()
        }
    }
    override suspend fun changePasswordForgotten(username: String, email: String, password: String): Int {
        val url = "$URL/changePasswordForgotten/$username/$email/$password"
        val client = HttpClient()
        try {
            return client.get<String>(url).toInt()

        } catch (e: Exception) {
            return -4
        } finally {
            client.close()
        }
    }

    override suspend fun getUser(username: String, password: String): String {
        val url = "$URL/user/$username/$password"
        val client = HttpClient()
        try {
            val result = client.get<String>(url)
            return result?.toString() ?: ""
        } catch (e: Exception) {
            return ""
        } finally {
            client.close()
        }
    }


    override suspend fun changePassword(
        username: String,
        password: String,
        passwordNew: String
    ): Int {
        val url = "$URL/newPassword/$username/$password/$passwordNew"

        return try {
            val client = HttpClient()
            val loginSuccess = this.login(username, password)

            when {
                loginSuccess && passwordNew != "" -> {
                    val result = client.get<String>(url).toBoolean()
                    if (result) 0 else -3 // -3 si no se cambió la contraseña después de la verificación exitosa
                }

                !loginSuccess -> -1 // La contraseña actual es incorrecta
                else -> -2 // Caso genérico
            }
        } catch (e: Exception) {
            -4 // Error en la base de datos
            Log.i("ERRROOOOR", e.printStackTrace().toString())
        }
    }

    override suspend fun setUniverseFav(username: String, universo: Universo?): Boolean {
        var universoSeleccionado: Int = 0
        if (universo == null) {
            universoSeleccionado = -1
        } else {
            universoSeleccionado = universo.getCodigo()
        }
        val url = "$URL/setUniverseFav/$username/${universoSeleccionado}"
        val client = HttpClient()
        Log.e("URL", url.toString())
        try {
            val result = client.get<String>(url)
            return result.toBoolean()
        } catch (e: Exception) {
            return false
        } finally {
            client.close()
        }
    }

    override suspend fun deleteUser(
        username: String,
        password: String,
        usernameToDelete: String
    ): Boolean{
        val url = "$URL/deleteUser/admin/admin/$usernameToDelete"
        val client = HttpClient()
        return try {
            val result = client.get<String>(url)
            when (result.toInt()) {
                0 -> {
                    true
                }
                -1 -> {
                    // EL USUARIO PARA BORRAR NO EXISTE
                    false
                }
                -2, -3, -4 -> {
                    // EL USUARIO NO ES ADMINISTRADOR
                    false
                }
                else -> {
                    // Handle other result codes if needed
                    false
                }
            }
        } catch (e: Exception) {
            // Handle exceptions, e.g., network issues
            false
        } finally {
            client.close()
        }
    }

    override suspend fun getUsers(): String? {
        val url = "$URL/getUsers/"
        val client = HttpClient()
        try {
            val result = client.get<String>(url)
            return result!!.toString()
        } catch (e: Exception) {
            return null
        } finally {
            client.close()
        }
    }


    override fun closeClient() {
        client.close()
    }
}
