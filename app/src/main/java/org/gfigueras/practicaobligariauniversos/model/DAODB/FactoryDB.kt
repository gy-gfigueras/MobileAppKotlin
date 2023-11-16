package org.gfigueras.practicaobligariauniversos.model.DAODB

import android.content.Context

object FactoryDB {
    public  val MODE_TEST: Int = 0
    public val MODE_SQLITE: Int = 1


    fun getDao(mode: Int, context: Context): IDAODB?{
        return when(mode){
            MODE_TEST       -> null
            MODE_SQLITE     -> DAODB(context)
            else            -> null
        }
    }
}