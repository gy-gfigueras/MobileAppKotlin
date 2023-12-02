package org.gfigueras.practicaobligariauniversos.model.DAOUsers

/**
 * FactoryServer
 * @return Retorna un DAO DAOUsers
 * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
 */
object FactoryServer {
    public val MODE_TEST: Int = 0
    public val MODE_MYSQL: Int = 1

    /**
     * getDAO Das un modo y te retorna una clase DAO
     * @param mode modo de factoria
     * @return DAOUsers o Null
     * @author Guillermo Figueras Jiménez <a href="https://github.com/GFigueras03">(GFIGUERAS)</a>
     */
    fun getDao(mode: Int): IDAOUsers?{
        return when(mode){
            MODE_TEST       -> null
            MODE_MYSQL      -> DAOUsers()
            else            -> null
        }
    }
}