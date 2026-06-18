# MiniDiarioEscolarRoom


## ¿Dónde se guardan los datos?

Los datos se guardan en una base de datos local usando Room.

Room trabaja sobre SQLite, pero de una forma más ordenada y ACTUAL. La base de datos se guarda dentro del almacenamiento privado de la app, o sea, dentro del espacio interno que Android le da a la aplicación.

No estoy usando almacenamiento externo, ni nada por el estilo.

---

## ¿Qué paso con SQLite ya no se usa?

SQLite sí se sigue usando, pero en este proyecto ya no lo manejo directamente con SQLiteOpenHelper.

Lo que entendí es que SQLite no está “muerto” como tal. Lo que ya no conviene tanto es hacer todo manualmente con SQLiteOpenHelper, porque se vuelve más pesado, más errores, menos ordenado y porque es viejo... y también porque me funaron jsjsjs

---

## ¿Por qué usé Room?

Usé Room porque permite separar mejor el proyecto.

En lugar de escribir toda la lógica de la base de datos directamente en una Activity o en un helper, Room permite organizar el código en varias partes:

* model: define las tablas.
* dao: define las consultas.
* database: crea la base de datos.
* repositories: conecta los DAO con los ViewModel.
* viewmodel: controla los datos que se muestran en pantalla.

---

## ¿Qué hace la carpeta converters?

La carpeta converters contiene la clase Converters.

La usé porque una nota guarda una fecha. Como SQLite no guarda directamente objetos como Date, se usa un convertidor para transformar la fecha a un tipo que SQLite pueda guardar.

En este caso, convierte Date a Long y de Long vuelve a Date.

---

## ¿Qué hace la carpeta viewmodel?

La carpeta viewmodel contiene la lógica que conecta los datos con la interfaz.

En mi caso tengo:

* UsuarioViewModel.kt
* NotaViewModel.kt
* UsuarioViewModelFactory.kt
* NotaViewModelFactory.kt

El ViewModel ayuda a que la pantalla no tenga toda la lógica mezclada. También permite manejar los datos de forma más ordenada.

Para las notas uso MutableStateFlow, para que la lista de notas se pueda actualizar cuando se agregan o eliminan registros.

---

## ¿Qué hace la carpeta adapter?

El adapter sirve para conectar la lista de notas con el RecyclerView.

El RecyclerView no sabe por sí solo cómo mostrar una nota. Por eso el adapter toma cada objeto Nota y lo coloca en el diseño de item_nota.xml.

---

## ¿Cuál es el control principal o maestro de los fragments?

En este proyecto, ProfileActivity es la Activity que controla el fragmento.

La pantalla tiene un FragmentContainerView, que funciona como el espacio donde se coloca el fragmento.

Después, desde ProfileActivity, se usa supportFragmentManager para cargar NotasFragment.

(Ya aprendí del error de hace rato, si me pase de *****)

---


## ¿Por que View Binding?

View Binding sirve para acceder a los elementos del XML de forma más segura.

Antes se usaba mucho findViewById, pero con View Binding el código queda más limpio y se evitan errores al buscar vistas.

## ¿Pantallazos?

No se si fue algo mio, pero al inicio daba pantallazos, pero si funciona, se lo prometo

## FIN


