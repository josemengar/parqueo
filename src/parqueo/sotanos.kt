package parqueo

class EdificioParqueos(
        val AllLevels: ArrayList<sotano>
){
    fun findLevel(searchName: String): sotano? {
        val filteredLevels = AllLevels.filter { it.nombre == searchName }
        if (filteredLevels.count() > 0) {
            return filteredLevels[0]
        }

        return null
    }
}