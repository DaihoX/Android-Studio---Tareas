fun main() {
    // Caso 1: Estudiante sin correo (usará el valor por defecto null)
    val estudiante1 = describirEstudiante(nombre = "Ana", edad = 12)
    println(estudiante1)

    // Caso 2: Estudiante con correo
    val estudiante2 = describirEstudiante(nombre = "Carlos", edad = 23, correo = "carlos@ucaldas.edu.co")
    println(estudiante2)
}

/**
 * Función que demuestra Null Safety.
 * El parámetro 'correo' es opcional y puede ser nulo.
 */
fun describirEstudiante(nombre: String, edad: Int, correo: String? = null): String {
    // Si 'correo' es nulo, se asigna el texto de la derecha. Si no, se usa el valor de 'correo'.
    val correoProcesado = correo ?: "Correo no registrado"
    
    return "Estudiante: $nombre, Edad: $edad, Correo: $correoProcesado"
}