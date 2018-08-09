package parqueo

import java.util.ArrayList
class sotano(
        var ancho: Int,
        var alto: Int,
        var nombre: String,
        var id: Int,
        var color: String,
        var muros: ArrayList<muros>,
        var park: ArrayList<park>,
        var placas: ArrayList<String>

){
    override fun toString(): String {
        return """
            nivel:
                nombre: $nombre
                Id: $id
                color: $color
        """.trimIndent()
    }
}