package org.gfigueras.practicaobligariauniversos.model.DAOUsers

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class DAOUsers : IDAOUsers {
    private val client = HttpClient()
    private val URL:String = "https://gfserver.onrender.com"

    override suspend fun login(username: String, password: String):Boolean {
        val url = "$URL/login/$username/$password"
        val client = HttpClient()

        try {
            return client.get<String>(url).toBoolean()
        }catch (e: Exception){
            return false
        } finally {
            client.close()
        }
    }

    override fun signUp(username: String, email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun closeClient() {
        client.close()
    }
}
