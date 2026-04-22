# ListaDeTareas

Aplicacion Android en Kotlin para gestionar tareas con las siguientes funciones:

- Agregar tareas con categoria (`Trabajo`, `Personal`, `Estudio`).
- Marcar tareas como completadas (tachando el texto).
- Editar el titulo de una tarea.
- Eliminar tareas.
- Ver contador de tareas pendientes.
- Persistir tareas localmente con `SharedPreferences`.

## Tecnologias

- Kotlin
- Android SDK (minSdk 24)
- RecyclerView + View Binding
- MaterialCardView

## Ejecucion

1. Abrir el proyecto en Android Studio.
2. Sincronizar Gradle.
3. Ejecutar en emulador o dispositivo Android.

Comando opcional para validar compilacion y pruebas unitarias:

```powershell
Push-Location "C:\Users\Jeison\AndroidStudioProjects\ListaDeTareas"
.\gradlew.bat testDebugUnitTest --no-daemon
Pop-Location
```

## Parte 7 - Preguntas y respuestas

### 1) Cual es la diferencia entre `val` y `var`?

- `val` declara una referencia inmutable: no se puede reasignar despues de inicializar.
- `var` declara una referencia mutable: si se puede reasignar.

### 2) Para que sirve el operador `?:` (Elvis)?

Sirve para dar un valor por defecto cuando una expresion nullable resulta `null`.

Ejemplo:

```kotlin
val correo: String? = null
val texto = correo ?: "No registrado"
```

### 3) Que genera automaticamente una `data class` que una clase normal no?

Una `data class` genera automaticamente metodos utiles como:

- `toString()`
- `equals()`
- `hashCode()`
- `copy()`
- `componentN()` para destructuring

### 4) Que hace el Adapter en un RecyclerView?

El `Adapter` conecta los datos con la interfaz de cada item del `RecyclerView`.

- Crea ViewHolders.
- Enlaza (bind) cada objeto de datos a sus vistas.
- Reporta cuandos elementos hay.
- Maneja eventos de cada fila (por ejemplo, editar/eliminar/completar).

### 5) Por que usar View Binding en lugar de `findViewById`?

Porque `View Binding`:

- Es mas seguro en compilacion (evita errores de casteo o IDs incorrectos).
- Reduce codigo repetitivo.
- Mejora legibilidad y mantenimiento.
- Da acceso directo y tipado a las vistas del layout.

