fun main() {
    // Definimos una lista de calificaciones (Double)
    val calificaciones = listOf(4.5, 3.0, 2.8, 5.0, 3.7, 1.5)

    // Llamamos a la función de procesamiento
    analizarCalificaciones(calificaciones)
}

/**
 * Función que recorre una lista de notas, calcula el promedio
 * y muestra el estado de cada estudiante.
 */
fun analizarCalificaciones(notas: List<Double>) {
    var sumaTotal = 0.0
    var aprobados = 0
    var reprobados = 0

    println("--- Reporte de Notas ---")

    // Uso del ciclo 'for' para recorrer la lista (Colección)
    for (nota in notas) {
        // Estructura de control para contar aprobados (nota >= 3.0)
        if (nota >= 3.0) {
            println("Nota: $nota -> APROBADO")
            aprobados++
        } else {
            println("Nota: $nota -> REPROBADO")
            reprobados++
        }
        sumaTotal += nota
    }

    // Cálculo del promedio
    val promedio = sumaTotal / notas.size

    // Resumen final con String Templates
    println("------------------------")
    println("Promedio General: ${String.format("%.2f", promedio)}")
    println("Total Aprobados: $aprobados")
    println("Total Reprobados: $reprobados")
}