[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/tc38IXJF)
# 📚 Trabajo Práctico: Sistema de Gestión de Biblioteca Digital (Java 21+)

## 📌 Objetivo General

Desarrollar un sistema de gestión de biblioteca digital que implemente los cinco principios SOLID, programación orientada a objetos, y conceptos avanzados de Java. El sistema deberá manejar diferentes tipos de recursos digitales, préstamos, reservas, y notificaciones en tiempo real.

## 👨‍🎓 Información del Alumno
- **Nombre y Apellido**: Stefano Palazzo


## 📑 Índice

- [📌 Objetivo General](#-objetivo-general)
- [👨‍🎓 Información del Alumno](#-información-del-alumno)
- [🧱 Arquitectura general](#-arquitectura-general)
- [🔄 Flujo de trabajo del sistema](#-flujo-de-trabajo-del-sistema)
- [⚙️ Cómo ponerlo en funcionamiento](#️-cómo-ponerlo-en-funcionamiento)
- [▶️ Instrucciones de ejecución](#️-instrucciones-de-ejecución)
- [🧪 Ejemplos de Prueba](#-ejemplos-de-prueba)
    - [📘 Gestión de Recursos](#1--gestión-de-recursos)
    - [👤 Gestión de Usuarios](#2--gestión-de-usuarios)
    - [🔁 Préstamos](#3--préstamos)
    - [📆 Reservas](#4--reservas)
    - [📊 Reportes](#5--reportes)
    - [🚨 Alertas](#6--alertas)
- [🧩 Tecnologías y Herramientas](#-tecnologías-y-herramientas)
- [📘 Etapas del Trabajo](#-etapas-del-trabajo)
- [📋 Detalle de Implementación](#-detalle-de-implementación)
- [✅ Entrega y Flujo de Trabajo con GitHub](#-entrega-y-flujo-de-trabajo-con-github)
- [📝 Ejemplo de Issue](#-ejemplo-de-issue)
- [✅ Requisitos para la Entrega](#-requisitos-para-la-entrega)
- [📚 Recursos Adicionales](#-recursos-adicionales)
- [📝 Consideraciones Éticas](#-consideraciones-éticas)
- [📝 Licencia](#-licencia)

## Documentación del Sistema

### 🧱 Arquitectura general

El sistema tiene la siguiente estructura:

```bash
src/
├── console/
│   ├── Consola.java
│   ├── ConsolaUsuarios.java
│   ├── ConsolaAlertas.java
│   ├── ConsolaRecursos.java
│   ├── ConsolaPrestamos.java
│   ├── ConsolaReservas.java
│   ├── ConsolaReportes.java
├── exceptions/
│   ├── RecursoNoDisponibleException.java
│   ├── UsuarioNoEncontradoException.java
├── interfaces/
│   ├── IServicioNotificaciones.java
│   ├── Prestable.java
├── models/
│   ├── Usuario.java
│   ├── RecursoDigital.java
│   ├── Prestamo.java
│   ├── Reserva.java
│   ├── CategoriaRecurso.java
├── services/
│   ├── GestorUsuarios.java
│   ├── GestorRecursos.java
│   ├── GestorPrestamos.java
│   ├── GestorReservas.java
│   ├── GestorNotificaciones.java
│   ├── AlertaVencimiento.java
│   ├── AlertaDisponibilidad.java
│   ├── ServicioNotificacionesEmail.java
```
El sistema está dividido en varios paquetes, organizados por responsabilidad:

- `console/`: Contiene las clases de interfaz de texto (consola) para la interacción con el usuario.
- `models/`: Define las entidades del dominio como `Usuario`, `RecursoDigital`, `Prestamo`, `Reserva`, etc.
- `services/`: Contiene los servicios que gestionan la lógica de negocio (`GestorUsuarios`, `GestorPrestamos`, etc.).
- `exceptions/`: Contiene excepciones personalizadas para manejar errores de dominio.
- `interfaces/`: Define interfaces como `Prestable` e `IServicioNotificaciones` para seguir los principios SOLID.

### 🔄 Flujo de trabajo del sistema

1. Al ejecutar la aplicación, se inicializan los gestores y servicios.
2. El menú principal permite al usuario navegar por las distintas funcionalidades: usuarios, recursos, préstamos, reservas, reportes y alertas.
3. Cada opción delega en una consola específica (`ConsolaUsuarios`, `ConsolaRecursos`, etc.) que se comunica con los gestores.
4. Los gestores manejan la lógica de negocio y validaciones, y notifican a los usuarios mediante un sistema de alertas (`GestorNotificaciones` y clases `AlertaDisponibilidad` / `AlertaVencimiento`).

---

## ⚙️ Cómo ponerlo en funcionamiento

### ✅ Requisitos previos

- Java 21 o superior instalado
- IDE recomendado: IntelliJ IDEA o Eclipse
- JDK correctamente configurado en el entorno

### ▶️ Instrucciones de ejecución

1. Clonar el repositorio o copiar el proyecto a tu máquina.
```bash
git clone https://github.com/um-programacion-ii/programacion-2-trabajo-practico-2-StefanoPalazzo.git
```
2. Abrir el proyecto en tu IDE favorito.
3. Compilar el proyecto.
4. Ejecutar la clase `app.Main` como programa Java.

También se puede compilar y ejecutar desde consola:

```bash
javac -d out $(find src -name "*.java")
java -cp out app.Main
```
---

### Prueba de Funcionalidades


#### 1. 📘 Gestión de Recursos

##### ➕ Agregar Libro
- **Pasos**:
    1. Seleccionar la opción "Agregar recurso" en el menú principal.
    2. Completar el formulario con los datos del libro.
- **Validación**:
    - Campos vacíos o duplicados generan errores.
- **Resultado esperado**:
    - El libro se agrega y aparece al listar recursos.

##### 🔍 Buscar Recurso
- **Pasos**:
    1. Seleccionar "Buscar recurso".
    2. Ingresar título, autor o palabra clave.
- **Casos de prueba**:
    - Buscar un recurso existente → aparece listado.
    - Buscar uno inexistente → muestra “sin resultados”.

##### 📋 Listar Recursos
- **Pasos**:
    1. Seleccionar "Listar todos los recursos".
    2. Aplicar filtros (por tipo, disponibilidad).
- **Resultado esperado**:
    - Se muestran los recursos organizados según el criterio elegido.

---

#### 2. 👤 Gestión de Usuarios

##### ➕ Registrar Usuario
- **Pasos**:
    1. Ingresar al módulo de usuarios.
    2. Seleccionar "Registrar nuevo usuario".
    3. Completar datos (nombre, email, tipo de usuario).
- **Validación**:
    - DNI debe ser único.
- **Resultado esperado**:
    - El usuario queda registrado y visible en la lista.

##### 🔎 Buscar Usuario
- **Pasos**:
    1. Ingresar a "Buscar usuario".
    2. Ingresar nombre o ID.
- **Resultado esperado**:
    - Se muestra la información del usuario si existe.

---

#### 3. 🔁 Préstamos

##### ➕ Realizar Préstamo
- **Pasos**:
    1. Ingresar a "Nuevo préstamo".
    2. Ingresar ID de recurso y de usuario.
  **Verificaciones**:
    - El recurso debe estar disponible.
- **Resultado esperado**:
    - El préstamo queda registrado correctamente.

##### ✔️ Devolver Recurso
- **Pasos**:
    1. Ingresar a "Devolver recurso".
    2. Ingresar el ID del préstamo o del recurso.
- **Resultado esperado**:
    - Se actualiza el estado del recurso a "disponible".

---

#### 4. 📆 Reservas

##### ➕ Realizar Reserva
- **Pasos**:
    1. Ingresar al módulo de reservas.
    2. Seleccionar recurso mediante id, agregar usuario mediante DNI y confirmar reserva.
- **Gestión**:
    - Se coloca al usuario en una cola de espera.
    - El sistema notifica cuando el recurso esté libre.

---

#### 5. 📊 Reportes

##### 📈 Ver Reportes
- **Pasos**:
    1. Ingresar al módulo de reportes.
    2. Elegir tipo de reporte: préstamos, recursos populares, usuarios activos.
- **Resultado esperado**:
    - Visualización gráfica o tabular.
    - Opción para exportar en CSV o JSON.

---

#### 6. 🚨 Alertas

##### 🔔 Verificar Alertas
- **Pasos**:
    1. Ingresar al sistema de alertas.
    2. Visualizar notificaciones de:
        - Recursos vencidos
        - Reservas listas
        - Penalizaciones activas
- **Gestión**:
    - Permite marcar alertas como leídas o resolverlas.

---

## 🧪 Ejemplos de Prueba

---

### Flujo Completo de Préstamo

#### 1. Registrar Usuario
```bash
# Seleccionar opción en menú principal
> 1. Gestor Usuarios
> 2. Agregar Usuario

# Ingresar datos
DNI: 21485792
NOMBRE: Juan 
APELLIDO: Pérez
EMAIL: juan.perez@example.com

Usuario registrado correctamente
```
#### 2. Agregar un Libro

```bash
# Seleccionar opción
> 2. Gestor Recursos
> 2. Agregar Recurso
> 1. Libro

# Ingresar datos
ID: 1
TITULO: Cien Años de Soledad
DESCRIPCION: Una novela de realismo mágico de Gabriel García Márquez.
ISBN: 978-3-16-148410-0
AUTOR: Gabriel García Márquez
EDITORIAL: Editorial XYZ
AÑO: 1967

Libro agregado al catálogo
```
#### 3. Realizar Préstamo
```bash
# Seleccionar opción
> 3. Gestor Prestamos
> 1. Prestar recurso

# Ingresar datos
ID Recurso: 1
ID Usuario: 21485792

Préstamo realizado con éxito
Thread-1 - RecursoDigital Cien Años de Soledad (1) prestado a Juan Pérez (21485792)
```
#### 4. Verificar Estado
```bash
# Seleccionar opción
> 2. Gestor Recursos
> 5. Buscar por ID

ID: 1

Titulo: Cien Años de Soledad
Estado: PRESTADO
Usuario: Juan Pérez
Fecha devolución: 2024-05-15
```

#### 5. Devolver Libro
```bash
# Seleccionar opción
> 3. Gestor Prestamos
> 2. Devolver prestamo

ID Prestamo: 1

Thread-1 - Préstamo devuelto con éxito: Cien Años de Soledad

# Verificación de reservas pendientes
El recurso tiene reservas pendientes, ¿desea procesar la reserva? (S/N)
>>>S
Reserva procesada: RecursoDigital Cien Años de Soledad (1) prestado a Abigail Nuñez (23478742)
```


### Sistema de Reservas

#### 1. Registrar Dos Usuarios
```bash
# Primer usuario
> 1. Gestor Usuarios
> 2. Agregar Usuario

DNI: 21485792
NOMBRE: Juan
APELLIDO: Pérez
EMAIL: juan.perez@mail.com

✅ Usuario registrado correctamente

# Segundo usuario
> 2. Agregar Usuario

DNI: 23478742
NOMBRE: Ana
APELLIDO: López  
EMAIL: ana.lopez@mail.com

✅ Usuario registrado correctamente
```

#### 2. Agregar Libro
```bash
> 2. Gestor Recursos
> 2. Agregar Recurso
> 1. Libro

ID: 1
TITULO: Java para Principiantes
DESCRIPCION: Guía introductoria a Java
ISBN: 978-0-13-708189-9
AUTOR: Juan Pérez
EDITORIAL: TechBooks
AÑO: 2024

✅ Libro agregado al catálogo
```

#### 3. Realizar Reservas
```bash
# Reserva Usuario 1
> 4. Gestor Reservas
> 1. Agregar Reserva

ID Recurso: 1
ID Usuario: 21485792

✅ Reserva agregada con éxito

# Reserva Usuario 2
> 1. Agregar Reserva

ID Recurso: 1  
ID Usuario: 23478742

✅ Reserva agregada con éxito
```

#### 4. Verificar Cola de Reservas
```bash
> 4. Gestor Reservas
> 4. Mostrar Reservas Activas

Reservas pendientes:
• Reserva #1: Java para Principiantes
Usuario: Juan Pérez (21485792)
Estado: EN_ESPERA

• Reserva #2: Java para Principiantes
Usuario: Ana López (23478742)  
Estado: EN_ESPERA
```

#### 5. Procesar Reservas
```bash
> 4. Gestor Reservas
> 2. Procesar Reserva de Recurso

ID Recurso: 1

✅ Reserva procesada:
RecursoDigital Java para Principiantes (1) prestado a Juan Pérez (21485792)
```

### Alertas y Notificaciones

#### 1. Realizar un Préstamo
```bash
# Seleccionar opción en el menú principal
> 3. Gestor Prestamos
> 1. Prestar recurso

# Ingresar datos
ID Recurso: 1
ID Usuario: 21485792

✅ Préstamo registrado correctamente
Thread-1 - RecursoDigital Java para Principiantes (1) prestado a Juan Pérez (21485792)
```
#### 2. Esperar a que se Acerque la Fecha de Vencimiento
```bash
Descripción: El sistema debe generar una alerta cuando se acerque la fecha de vencimiento del préstamo.
Resultado Esperado: El sistema genera una alerta que dice: "El recurso 'Java para Principiantes' debe devolverse en 2 días."

# El sistema genera automáticamente una alerta cuando se acerca la fecha de vencimiento
[WARNING] El recurso 'Java para Principiantes' debe devolverse en 2 días.
```

3. **Verificar las Alertas Generadas**
```bash
# Seleccionar opción en el menú principal
> 6. Alertas
> 2. Historial de alertas

=== HISTORIAL DE ALERTAS ===
[INFO] Préstamo registrado correctamente.
[WARNING] El recurso 'Java para Principiantes' debe devolverse en 2 días.
```
4. **Probar la Renovación del Préstamo**
```bash
# Seleccionar opción en el menú principal
> 3. Gestor Prestamos
> 3. Renovar préstamo

# Ingresar ID del préstamo
ID Prestamo: 1

Préstamo renovado correctamente
Nueva fecha de devolución: 2024-05-20 
```
## 🧩 Tecnologías y Herramientas

- Java 21+ (LTS)
- Git y GitHub
- GitHub Projects
- GitHub Issues
- GitHub Pull Requests

## 📘 Etapas del Trabajo

### Etapa 1: Diseño Base y Principios SOLID
- **SRP**: 
  - Crear clase `Usuario` con atributos básicos (nombre, ID, email)
  - Crear clase `RecursoDigital` como clase base abstracta
  - Implementar clase `GestorUsuarios` separada de `GestorRecursos`
  - Cada clase debe tener una única responsabilidad clara
  - Implementar clase `Consola` para manejar la interacción con el usuario

- **OCP**: 
  - Diseñar interfaz `RecursoDigital` con métodos comunes
  - Implementar clases concretas `Libro`, `Revista`, `Audiolibro`
  - Usar herencia para extender funcionalidad sin modificar código existente
  - Ejemplo: agregar nuevo tipo de recurso sin cambiar clases existentes
  - Implementar menú de consola extensible para nuevos tipos de recursos

- **LSP**: 
  - Asegurar que todas las subclases de `RecursoDigital` puedan usarse donde se espera `RecursoDigital`
  - Implementar métodos comunes en la clase base
  - Validar que el comportamiento sea consistente en todas las subclases
  - Crear métodos de visualización en consola para todos los tipos de recursos

- **ISP**: 
  - Crear interfaz `Prestable` para recursos que se pueden prestar
  - Crear interfaz `Renovable` para recursos que permiten renovación
  - Implementar solo las interfaces necesarias en cada clase
  - Diseñar menús de consola específicos para cada tipo de operación

- **DIP**: 
  - Crear interfaz `ServicioNotificaciones`
  - Implementar `ServicioNotificacionesEmail` y `ServicioNotificacionesSMS`
  - Usar inyección de dependencias en las clases que necesitan notificaciones
  - Implementar visualización de notificaciones en consola

### Etapa 2: Gestión de Recursos y Colecciones
- Implementar colecciones:
  - Usar `ArrayList<RecursoDigital>` para almacenar recursos
  - Usar `Map<String, Usuario>` para gestionar usuarios
  - Implementar métodos de búsqueda básicos
  - Crear menú de consola para gestión de recursos

- Crear servicios de búsqueda:
  - Implementar búsqueda por título usando Streams
  - Implementar filtrado por categoría
  - Crear comparadores personalizados para ordenamiento
  - Diseñar interfaz de consola para búsquedas con filtros

- Sistema de categorización:
  - Crear enum `CategoriaRecurso`
  - Implementar método de asignación de categorías
  - Crear búsqueda por categoría
  - Mostrar categorías disponibles en consola

- Manejo de excepciones:
  - Crear `RecursoNoDisponibleException`
  - Crear `UsuarioNoEncontradoException`
  - Implementar manejo adecuado de excepciones en los servicios
  - Mostrar mensajes de error amigables en consola

### Etapa 3: Sistema de Préstamos y Reservas
- Implementar sistema de préstamos:
  - Crear clase `Prestamo` con atributos básicos
  - Implementar lógica de préstamo y devolución
  - Manejar estados de los recursos (disponible, prestado, reservado)
  - Diseñar menú de consola para préstamos

- Sistema de reservas:
  - Crear clase `Reserva` con atributos necesarios
  - Implementar cola de reservas usando `BlockingQueue`
  - Manejar prioridad de reservas
  - Mostrar estado de reservas en consola

- Notificaciones:
  - Implementar sistema básico de notificaciones
  - Crear diferentes tipos de notificaciones
  - Usar `ExecutorService` para enviar notificaciones
  - Mostrar notificaciones en consola

- Concurrencia:
  - Implementar sincronización en operaciones de préstamo
  - Usar `synchronized` donde sea necesario
  - Manejar condiciones de carrera
  - Mostrar estado de operaciones concurrentes en consola

### Etapa 4: Reportes y Análisis
- Generar reportes básicos:
  - Implementar reporte de recursos más prestados
  - Crear reporte de usuarios más activos
  - Generar estadísticas de uso por categoría
  - Diseñar visualización de reportes en consola

- Sistema de alertas:
  - Implementar alertas por vencimiento de préstamos:
    - Crear clase `AlertaVencimiento` que monitorea fechas de devolución
    - Implementar lógica de recordatorios (1 día antes, día del vencimiento)
    - Mostrar alertas en consola con formato destacado
    - Permitir renovación desde la alerta
  
  - Crear notificaciones de disponibilidad:
    - Implementar `AlertaDisponibilidad` para recursos reservados
    - Notificar cuando un recurso reservado está disponible
    - Mostrar lista de recursos disponibles en consola
    - Permitir préstamo inmediato desde la notificación
  
  - Manejar recordatorios automáticos:
    - Implementar sistema de recordatorios periódicos
    - Crear diferentes niveles de urgencia (info, warning, error)
    - Mostrar historial de alertas en consola
    - Permitir configuración de preferencias de notificación

- Concurrencia en reportes:
  - Implementar generación de reportes en segundo plano
  - Usar `ExecutorService` para tareas asíncronas
  - Manejar concurrencia en acceso a datos
  - Mostrar progreso de generación de reportes en consola

## 📋 Detalle de Implementación

### 1. Estructura Base
```java
// Interfaces principales
public interface RecursoDigital {
    String getIdentificador();
    EstadoRecurso getEstado();
    void actualizarEstado(EstadoRecurso estado);
}

public interface Prestable {
    boolean estaDisponible();
    LocalDateTime getFechaDevolucion();
    void prestar(Usuario usuario);
}

public interface Notificable {
    void enviarNotificacion(String mensaje);
    List<Notificacion> getNotificacionesPendientes();
}

// Clase base abstracta
public abstract class RecursoBase implements RecursoDigital, Prestable {
    // Implementación común
}
```

### 2. Gestión de Biblioteca
```java
public class GestorBiblioteca {
    private final Map<String, RecursoDigital> recursos;
    private final List<Prestamo> prestamos;
    private final ExecutorService notificador;
    // Implementación de gestión
}
```

### 3. Sistema de Préstamos
```java
public class SistemaPrestamos {
    private final BlockingQueue<SolicitudPrestamo> colaSolicitudes;
    private final ExecutorService procesadorPrestamos;
    // Implementación de préstamos
}
```

## ✅ Entrega y Flujo de Trabajo con GitHub

1. **Configuración del Repositorio**
   - Proteger la rama `main`
   - Crear template de Issues y Pull Requests

2. **Project Kanban**
   - `To Do`
   - `In Progress`
   - `Code Review`
   - `Done`

3. **Milestones**
   - Etapa 1: Diseño Base
   - Etapa 2: Gestión de Recursos
   - Etapa 3: Sistema de Préstamos
   - Etapa 4: Reportes

4. **Issues y Pull Requests**
   - Crear Issues detallados para cada funcionalidad
   - Asociar cada Issue a un Milestone
   - Implementar en ramas feature
   - Revisar código antes de merge

## 📝 Ejemplo de Issue

### Título
Implementar sistema de préstamos concurrente

### Descripción
Crear el sistema de préstamos que utilice hilos y el patrón productor-consumidor para procesar solicitudes de préstamo en tiempo real.

#### Requisitos
- Implementar `BlockingQueue` para solicitudes de préstamo
- Crear procesador de solicitudes usando `ExecutorService`
- Implementar sistema de notificaciones
- Asegurar thread-safety en operaciones de préstamo

#### Criterios de Aceptación
- [ ] Sistema procesa préstamos concurrentemente
- [ ] Manejo adecuado de excepciones
- [ ] Documentación de diseño

### Labels
- `enhancement`
- `concurrency`

## ✅ Requisitos para la Entrega

- ✅ Implementación completa de todas las etapas
- ✅ Código bien documentado
- ✅ Todos los Issues cerrados
- ✅ Todos los Milestones completados
- ✅ Pull Requests revisados y aprobados
- ✅ Project actualizado

> ⏰ **Fecha de vencimiento**: 23/04/2025 a las 13:00 hs

## 📚 Recursos Adicionales

- Documentación oficial de Java 21
- Guías de estilo de código
- Ejemplos de implementación concurrente
- Patrones de diseño aplicados

## 📝 Consideraciones Éticas

### Uso de Inteligencia Artificial
El uso de herramientas de IA en este trabajo práctico debe seguir las siguientes pautas:

1. **Transparencia**
   - Documentar claramente qué partes del código fueron generadas con IA
   - Explicar las modificaciones realizadas al código generado
   - Mantener un registro de las herramientas utilizadas

2. **Aprendizaje**
   - La IA debe usarse como herramienta de aprendizaje, no como reemplazo
   - Comprender y ser capaz de explicar el código generado
   - Utilizar la IA para mejorar la comprensión de conceptos

3. **Integridad Académica**
   - El trabajo final debe reflejar tu aprendizaje y comprensión personal
   - No se permite la presentación de código generado sin comprensión
   - Debes poder explicar y defender cualquier parte del código

4. **Responsabilidad**
   - Verificar la corrección y seguridad del código generado
   - Asegurar que el código cumple con los requisitos del proyecto
   - Mantener la calidad y estándares de código establecidos

5. **Desarrollo Individual**
   - La IA puede usarse para facilitar tu proceso de aprendizaje
   - Documentar tu proceso de desarrollo y decisiones tomadas
   - Mantener un registro de tu progreso y aprendizaje

### Consecuencias del Uso Inadecuado
El uso inadecuado de IA puede resultar en:
- Calificación reducida o nula
- Sanciones académicas
- Pérdida de oportunidades de aprendizaje
- Impacto negativo en tu desarrollo profesional

## 📝 Licencia

Este trabajo es parte del curso de Programación Avanzada de Ingeniería en Informática. Uso educativo únicamente.