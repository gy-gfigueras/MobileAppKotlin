package org.gfigueras.practicaobligariauniversos.model.DAODB
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.AssetManager
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.gfigueras.practicaobligariauniversos.model.entities.Mapa
import org.gfigueras.practicaobligariauniversos.model.entities.Universo

import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class DAODB(context: Context) : IDAODB,
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "universos.db"
    }

    private val assets: AssetManager
    private var databaseDir: String = ""

    init {
        assets = context.assets
        databaseDir = context.applicationInfo.dataDir + "/databases/"
        val file = File(databaseDir)
        if (!file.exists()) file.mkdir()
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Nothing, as we do not want to create or insert any data at the initialization.
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        copyDatabase()
    }

    override fun getWritableDatabase(): SQLiteDatabase {
        if (!doesDatabaseExist()) copyDatabase()
        return super.getWritableDatabase()
    }

    override fun getReadableDatabase(): SQLiteDatabase {
        if (!doesDatabaseExist()) copyDatabase()
        return super.getReadableDatabase()
    }

    private fun doesDatabaseExist(): Boolean {
        return File(databaseDir + DATABASE_NAME).exists()
    }

    private fun copyDatabase() {
        try {
            val inputStream = assets.open("databases/$DATABASE_NAME")
            val outputStream = FileOutputStream(databaseDir + DATABASE_NAME)
            val buffer = ByteArray(8 * 1024)

            var readed: Int

            while (inputStream.read(buffer).also { readed = it } != -1) {
                outputStream.write(buffer, 0, readed)
            }

            outputStream.flush()
            outputStream.close()
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @SuppressLint("Range")
    override fun listUniversos(): MutableList<Universo> {
        val DB:SQLiteDatabase = readableDatabase
        val UNIVERSOS:MutableList<Universo> = ArrayList()

        val QUERY:String ="SELECT cod, nombre, descripcion, imagen FROM Universo"
        val CURSOR:Cursor = DB.rawQuery(QUERY, null)
        try {
            while (CURSOR.moveToNext()){
                val CODIGO:Int          = CURSOR.getInt(CURSOR.getColumnIndex("cod"))
                val NOMBRE:String       = CURSOR.getString(CURSOR.getColumnIndex("nombre"))
                val DESCRIPCION:String  = CURSOR.getString(CURSOR.getColumnIndex("descripcion"))
                val IMAGEN:String       = CURSOR.getString(CURSOR.getColumnIndex("imagen"))

                UNIVERSOS.add(Universo(CODIGO,NOMBRE,DESCRIPCION,IMAGEN))

            }
        }finally {
            CURSOR.close()
        }

        DB.close()
        return UNIVERSOS
    }

    @SuppressLint("Range")
    override fun getUniverso(cod:Int): Universo?{
        val DB:SQLiteDatabase = readableDatabase
        var universo: Universo? = null

        val QUERY: String = "SELECT cod, nombre, descripcion, imagen FROM Universo WHERE cod = ${cod}"
        val CURSOR:Cursor = DB.rawQuery(QUERY, emptyArray())

        try {
            if(CURSOR.moveToFirst()){
                val CODIGO:Int          = CURSOR.getInt(CURSOR.getColumnIndex("cod"))
                val NOMBRE:String       = CURSOR.getString(CURSOR.getColumnIndex("nombre"))
                val DESCRIPCION:String  = CURSOR.getString(CURSOR.getColumnIndex("descripcion"))
                val IMAGEN:String       = CURSOR.getString(CURSOR.getColumnIndex("imagen"))

                universo = Universo(CODIGO,NOMBRE,DESCRIPCION,IMAGEN)
            }
        }finally {
            CURSOR.close()
        }
        return universo

    }
    @SuppressLint("Range")
    override fun getUniverso(name: String): Universo? {
        val DB:SQLiteDatabase = readableDatabase
        var universo: Universo? = null

        val QUERY: String = "SELECT cod, nombre, descripcion, imagen FROM Universo WHERE nombre = '$name'"
        val CURSOR:Cursor = DB.rawQuery(QUERY, emptyArray())

        try {
            if(CURSOR.moveToFirst()){
                val CODIGO:Int          = CURSOR.getInt(CURSOR.getColumnIndex("cod"))
                val NOMBRE:String       = CURSOR.getString(CURSOR.getColumnIndex("nombre"))
                val DESCRIPCION:String  = CURSOR.getString(CURSOR.getColumnIndex("descripcion"))
                val IMAGEN:String       = CURSOR.getString(CURSOR.getColumnIndex("imagen"))

                universo = Universo(CODIGO,NOMBRE,DESCRIPCION,IMAGEN)
            }
        }finally {
            CURSOR.close()
        }
        return universo
    }

    @SuppressLint("Range")
    override fun listMapas(): MutableList<Mapa> {
        val DB:SQLiteDatabase = readableDatabase
        val MAPAS:MutableList<Mapa> = ArrayList()

        val QUERY:String ="SELECT cod, nombre, descripcion, imagen, universo FROM Mapa"
        val CURSOR:Cursor = DB.rawQuery(QUERY, null)

        try {
            while (CURSOR.moveToNext()){
                val CODIGO:Int          = CURSOR.getInt(CURSOR.getColumnIndex("cod"))
                val NOMBRE:String       = CURSOR.getString(CURSOR.getColumnIndex("nombre"))
                val DESCRIPCION:String  = CURSOR.getString(CURSOR.getColumnIndex("descripcion"))
                val IMAGEN:String       = CURSOR.getString(CURSOR.getColumnIndex("imagen"))
                val UNIVERSO:Int        = CURSOR.getInt(CURSOR.getColumnIndex("universo"))

                MAPAS.add(Mapa(CODIGO,NOMBRE,DESCRIPCION,IMAGEN,getUniverso(UNIVERSO)!!))

            }
        }finally {
            CURSOR.close()
        }

        DB.close()
        return MAPAS
    }

    @SuppressLint("Range")
    override fun getMapa(cod: Int): Mapa? {
        val DB:SQLiteDatabase = readableDatabase
        var mapa: Mapa? = null

        val QUERY: String = "SELECT cod, nombre, descripcion, imagen, universo FROM Mapa WHERE cod = ${cod}"
        val CURSOR:Cursor = DB.rawQuery(QUERY, emptyArray())

        try {
            if(CURSOR.moveToFirst()){
                val CODIGO:Int          = CURSOR.getInt(CURSOR.getColumnIndex("cod"))
                val NOMBRE:String       = CURSOR.getString(CURSOR.getColumnIndex("nombre"))
                val DESCRIPCION:String  = CURSOR.getString(CURSOR.getColumnIndex("descripcion"))
                val IMAGEN:String       = CURSOR.getString(CURSOR.getColumnIndex("imagen"))
                val UNIVERSO:Int        = CURSOR.getInt(CURSOR.getColumnIndex("universo"))

                mapa = Mapa(CODIGO,NOMBRE,DESCRIPCION,IMAGEN,getUniverso(UNIVERSO)!!)
            }
        }finally {
            CURSOR.close()
        }
        return mapa
    }

    @SuppressLint("Range")
    override fun getMapa(name: String): Mapa? {
        val DB:SQLiteDatabase = readableDatabase
        var mapa: Mapa? = null

        val QUERY: String = "SELECT cod, nombre, descripcion, imagen, universo FROM Mapa WHERE nombre = '${name}''"
        val CURSOR:Cursor = DB.rawQuery(QUERY, emptyArray())

        try {
            if(CURSOR.moveToFirst()){
                val CODIGO:Int          = CURSOR.getInt(CURSOR.getColumnIndex("cod"))
                val NOMBRE:String       = CURSOR.getString(CURSOR.getColumnIndex("nombre"))
                val DESCRIPCION:String  = CURSOR.getString(CURSOR.getColumnIndex("descripcion"))
                val IMAGEN:String       = CURSOR.getString(CURSOR.getColumnIndex("imagen"))
                val UNIVERSO:Int        = CURSOR.getInt(CURSOR.getColumnIndex("universo"))

                mapa = Mapa(CODIGO,NOMBRE,DESCRIPCION,IMAGEN,getUniverso(UNIVERSO)!!)
            }
        }finally {
            CURSOR.close()
        }
        return mapa
    }
    @SuppressLint("Range")
    override fun getMapas(mundo: String): MutableList<Mapa>? {
        val DB:SQLiteDatabase = readableDatabase
        val MAPAS:MutableList<Mapa> = ArrayList()
        val universo:Int? = getUniverso(mundo)!!.getCodigo()

        val QUERY:String ="SELECT cod, nombre, descripcion, imagen, universo FROM Mapa WHERE universo = ${universo}"
        val CURSOR:Cursor = DB.rawQuery(QUERY, null)
        try {
            while (CURSOR.moveToNext()){
                val CODIGO:Int          = CURSOR.getInt(CURSOR.getColumnIndex("cod"))
                val NOMBRE:String       = CURSOR.getString(CURSOR.getColumnIndex("nombre"))
                val DESCRIPCION:String  = CURSOR.getString(CURSOR.getColumnIndex("descripcion"))
                val IMAGEN:String       = CURSOR.getString(CURSOR.getColumnIndex("imagen"))
                val UNIVERSO:Int        = CURSOR.getInt(CURSOR.getColumnIndex("universo"))

                MAPAS.add(Mapa(CODIGO,NOMBRE,DESCRIPCION,IMAGEN,getUniverso(UNIVERSO)!!))

            }
        }finally {
            CURSOR.close()
        }

        DB.close()
        return MAPAS
    }
    @SuppressLint("Range")
    override fun getMapas(mundo: Int): MutableList<Mapa>? {
        val DB:SQLiteDatabase = readableDatabase
        val MAPAS:MutableList<Mapa> = ArrayList()
        val universo:Int = getUniverso(mundo)!!.getCodigo()

        val QUERY:String ="SELECT cod, nombre, descripcion, imagen, universo FROM Mapa WHERE universo = ${universo}"
        val CURSOR:Cursor = DB.rawQuery(QUERY, null)
        try {
            while (CURSOR.moveToNext()){
                val CODIGO:Int          = CURSOR.getInt(CURSOR.getColumnIndex("cod"))
                val NOMBRE:String       = CURSOR.getString(CURSOR.getColumnIndex("nombre"))
                val DESCRIPCION:String  = CURSOR.getString(CURSOR.getColumnIndex("descripcion"))
                val IMAGEN:String       = CURSOR.getString(CURSOR.getColumnIndex("imagen"))
                val UNIVERSO:Int        = CURSOR.getInt(CURSOR.getColumnIndex("universo"))

                MAPAS.add(Mapa(CODIGO,NOMBRE,DESCRIPCION,IMAGEN,getUniverso(UNIVERSO)!!))

            }
        }finally {
            CURSOR.close()
        }

        DB.close()
        return MAPAS
    }


}

