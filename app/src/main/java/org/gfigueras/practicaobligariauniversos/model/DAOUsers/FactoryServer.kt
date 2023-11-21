package org.gfigueras.practicaobligariauniversos.model.DAOUsers

object FactoryServer {
    public val MODE_TEST: Int = 0
    public val MODE_MYSQL: Int = 1


    fun getDao(mode: Int): IDAOUsers?{
        return when(mode){
            MODE_TEST       -> null
            MODE_MYSQL      -> DAOUsers()
            else            -> null
        }
    }
}