package org.gfigueras.practicaobligariauniversos.controller

import org.gfigueras.practicaobligariauniversos.model.DAODB.FactoryDB
import android.content.Context
import org.gfigueras.practicaobligariauniversos.model.DAODB.IDAODB
import org.gfigueras.practicaobligariauniversos.model.DAOUsers.FactoryServer
import org.gfigueras.practicaobligariauniversos.model.DAOUsers.IDAOUsers
import org.gfigueras.practicaobligariauniversos.model.entities.Mapa
import org.gfigueras.practicaobligariauniversos.model.entities.Universo
import org.gfigueras.practicaobligariauniversos.model.entities.User

class Controller(context: Context): IController {
    private var dbdao: IDAODB? = null
    private var daousers: IDAOUsers? = null

    companion object{
        public var userSaved: User? = null
        public var universoSaved:Universo? = null
        public var usuarios:MutableList<User>? = null

    }
    init{
        dbdao = FactoryDB.getDao(FactoryDB.MODE_SQLITE, context)
        daousers = FactoryServer.getDao(FactoryServer.MODE_MYSQL)
    }
    override fun listUniversos(): MutableList<Universo> {
        return dbdao!!.listUniversos()
    }

    override fun getUniverso(cod: Int): Universo? {
        return dbdao!!.getUniverso(cod)
    }

    override fun getUniverso(name: String): Universo? {
        return dbdao!!.getUniverso(name)
    }

    override fun listMapas(): MutableList<Mapa> {
        return dbdao!!.listMapas()
    }

    override fun getMapa(cod: Int): Mapa? {
        return dbdao!!.getMapa(cod)
    }

    override fun getMapa(name: String): Mapa? {
        return dbdao!!.getMapa(name)
    }

    override fun getMapas(mundo: String): MutableList<Mapa>? {
        return dbdao!!.getMapas(mundo)
    }

    override fun getMapas(mundo: Int): MutableList<Mapa>? {
       return  dbdao!!.getMapas(mundo)
    }

    override suspend fun login(username: String, password: String): Boolean {
        return daousers!!.login(username, password)
    }

    override suspend fun signUp(username: String, email: String, password: String): Int {
        return daousers!!.signUp(username,email,password)
    }

    override suspend fun getUser(username: String, password: String): String{
        return daousers!!.getUser(username, password)
    }

    override suspend fun changePassword(
        username: String,
        password: String,
        passwordNew: String
    ): Int {
        return  daousers!!.changePassword(username,password,passwordNew)
    }


    override suspend fun setUniverseFav(
        username: String,
        universo: Universo?
    ): Boolean {
        return daousers!!.setUniverseFav(username,universo)
    }

    override suspend fun getUsers(): String? {
        return daousers!!.getUsers()
    }

    override suspend fun deleteUser(
        username: String,
        password: String,
        usernameToDelete: String
    ): Boolean {
        return daousers!!.deleteUser(username,password,usernameToDelete)
    }


}