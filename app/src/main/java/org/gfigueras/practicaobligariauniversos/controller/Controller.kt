package org.gfigueras.practicaobligariauniversos.controller

import org.gfigueras.practicaobligariauniversos.model.DAODB.FactoryDB
import android.content.Context
import org.gfigueras.practicaobligariauniversos.model.DAODB.IDAODB
import org.gfigueras.practicaobligariauniversos.model.entities.Mapa
import org.gfigueras.practicaobligariauniversos.model.entities.Universo

class Controller(context: Context): IController {
    private var dbdao: IDAODB? = null
    init{
        dbdao = FactoryDB.getDao(FactoryDB.MODE_SQLITE, context)
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


}