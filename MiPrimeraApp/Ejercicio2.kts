fun main() {
    val numero1 = 10.0
    val numero2 = 5.0
    val numeroCero = 0.0

    // Pruebas en consola (Logcat)
    println("Suma: ${sumar(numero1, numero2)}")
    println("Resta: ${restar(numero1, numero2)}")
    println("Multiplicación: ${multiplicar(numero1, numero2)}")

    // Prueba de división normal y división por cero
    println("División normal: ${dividir(numero1, numero2)}")
    println("División por cero: ${dividir(numero1, numeroCero)}")
}

// 1. Funciones de una sola línea (Single-Expression).
// Kotlin infiere que devuelven un Double gracias al '='
fun sumar(a: Double, b: Double): Double = a + b

fun restar(a: Double, b: Double): Double = a - b

fun multiplicar(a: Double, b: Double): Double = a * b

// 2. Función tradicional con llaves y manejo de errores
fun dividir(a: Double, b: Double): Double {
    // Lógica de control para evitar que la aplicación se rompa al dividir por 0
    if (b == 0.0) {
        println("Error: No se puede dividir por cero.")
        return 0.0 // Retornamos 0.0 como valor de seguridad
    }
    return a / b
}