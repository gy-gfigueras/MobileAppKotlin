package org.gfigueras.practicaobligariauniversos.model.DAODB

import android.content.Context
/**
 * FactoryDB
 * @return Retorna un DAO DAODB
 * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
 */
object FactoryDB {
    public  val MODE_TEST: Int = 0
    public val MODE_SQLITE: Int = 1

    /**
     * getDAO Das un modo y te retorna una clase DAODB
     * @param mode modo de factoria
     * @return DAODB o Null
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getDao(mode: Int, context: Context): IDAODB?{
        return when(mode){
            MODE_TEST       -> null
            MODE_SQLITE     -> DAODB(context)
            else            -> null
        }
    }
}