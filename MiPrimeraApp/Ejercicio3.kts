fun main() {
    // Pruebas en el Logcat simulando diferentes cantidades de un producto en el inventario
    println("Producto A: ${verificarInventario(0)}")   // Prueba de producto agotado
    println("Producto B: ${verificarInventario(5)}")   // Prueba de stock bajo
    println("Producto C: ${verificarInventario(25)}")  // Prueba de stock suficiente
    println("Producto D: ${verificarInventario(-2)}")  // Prueba de error (número negativo)
}

/**
 * Función que evalúa la cantidad de un producto y retorna su estado.
 * Se utiliza 'when' como expresión para retornar directamente el String resultante.
 */
fun verificarInventario(cantidad: Int): String {

    // 'when' evalúa la variable 'cantidad' bloque por bloque de arriba hacia abajo
    return when {
        cantidad < 0 -> "Error: La cantidad no puede ser negativa."
        cantidad == 0 -> "Agotado: No hay unidades disponibles."
        cantidad in 1..10 -> "Alerta: Stock bajo, quedan solo $cantidad unidades." // Uso de rangos (in)
        else -> "Disponible: Hay stock suficiente ($cantidad unidades)." // 'else' actúa como el caso por defecto
    }
}