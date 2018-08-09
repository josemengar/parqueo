import parqueo.sotano
import parqueo.muros
import parqueo.park
import parqueo.sotanos
import java.io.FileNotFoundException
import java.io.File
import java.util.*
import java.util.ArrayList

fun getmenu(hasusuario: Boolean, usuarioad: Boolean ):String{
    if (!hasusuario){
        return """
            Menu:
                1. Administrador
                2. Conductor
                3. Salir
        """.trimIndent()
    }
    if(usuarioad){
        return """
            Menu:
                1. Crear nivel
                2. Eliminar nivel
                3. Ver todos los niveles
                4. Salir
                """.trimIndent()
    }
    return """
        Menu:
            1.Ingresar Placa
            2.Salir
            """.trimIndent()
}
fun leer(link: String): ArrayList<Array<String>> {
    val MapaIngresado = ArrayList<Array<String>>()
    try {
        val scan = Scanner(File(link))
        while (scan.hasNextLine()) {
            val line = scan.nextLine()
            MapaIngresado.add(line.trim({ it <= ' ' }).split("".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray())
        }
    } catch (ex: FileNotFoundException) {
    }
    return MapaIngresado
}

private fun buildLevel(MapaIngresado: ArrayList<Array<String>>, name: String, id: Int, color: String): sotano {
    val MiNivel = sotano(MapaIngresado[0].size, MapaIngresado.size,name, id, color, ArrayList(), ArrayList(), ArrayList())
    for (ancho in 0 until MiNivel.alto) {
        for (alto in 0 until MiNivel.ancho) {
            val toEvaluate = MapaIngresado[ancho][alto]
            when (toEvaluate) {
                " " -> {
                }
                "*" -> {
                    val misMuros = muros(ancho, alto)
                    MiNivel.muros.add(misMuros)
                }
                else -> {
                    val misParqueos = park(ancho, alto, false)
                    MiNivel.park.add(misParqueos)
                }
            }
        }
    }
    return MiNivel
}

fun main(args: Array<String>){
    var conti=true
    var user = false
    var admin = false
    var lot = sotanos(ArrayList())
    do{
        println(getmenu(user, admin))
        print("Ingrese una opcion: ")
        val strOption = readLine()!!
        val option = strOption.toInt()
        if (!user){
            when(option){
                1-> {
                    user = true
                    admin = true
                }
                2-> {
                    user = true
                }
                3 ->{
                    conti = false
                }

            }
        }else if(admin){
            when(option){
                1-> {
                    println("Ingrese el nombre del nivel nuevo: ")
                    val name = readLine()!!
                    println("Ingrese el identificador del nivel: ")
                    val id = readLine()!!.toInt()
                    println("Ingrese el color del nivel")
                    val color = readLine()!!
                    println("Ingrese el archivo donde se encuentra la estructura del nivel: ")
                    val link = readLine()!!
                    val lectura = leer(link)
                    val NewNivel=buildLevel(lectura, name, id, color)
                    lot.AllLevels.add(NewNivel)
                }
                2-> {
                    println("Ingrese el nombre del nivel que desea borrar: ")
                    val borrar = readLine().toString()
                    var buscar = lot.findLevel(borrar)
                    if (buscar != null){
                        lot.AllLevels.remove(buscar)
                    }
                }
                3 ->{ println(lot.AllLevels)
                }
                4 ->{
                    admin=false
                    user = false
                }
            }
        }else {
            when (option) {
                1 -> {
                    println("Ingrese el numero de su placa")
                    var nombre = readLine().toString()
                    for(nivel in lot.AllLevels){
                        var busqueda=nivel.placas.contains(nombre)
                        if (busqueda != null){
                            nivel.placas.add(nombre)
                        }
                        else{
                            var Mero = nivel
                            println(Mero)
                        }
                    }
                }
                2 -> {
                    user = false
                }
            }
        }

    }while(conti)

}