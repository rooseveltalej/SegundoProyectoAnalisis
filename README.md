# Segundo Proyecto de Analisis de Algoritmos


El presente proyecto consiste en desarrollar un programa en Java que resuelva el problema de la mochila utilizando dos estrategias de diseño diferentes: programación dinámica y algoritmos genéticos. A continuación, un resumen de los principales requerimientos:

### Representación de objetos:
Se debe construir una única representación de los objetos, cada uno con su peso y valor, junto con la capacidad máxima de la mochila.
### Programación dinámica:
Implementar un algoritmo de programación dinámica para resolver el problema de la mochila, utilizando una tabla de dimensiones (cantidad de objetos x capacidad máxima de la mochila).
### Algoritmo genético: 
Implementar un algoritmo genético, con los siguientes componentes:

- Población inicial aleatoria o utilizando un algoritmo voraz.
- Cromosomas representando la presencia/ausencia de objetos en la mochila.
- Función de aptitud basada en el valor total de los objetos seleccionados.
- Cruces para generar nuevos cromosomas a partir de los padres.
- Mutaciones para evitar poblaciones repetidas o que excedan la capacidad de la mochila.
- Generación de 20 iteraciones, guardando los 5 mejores resultados.


### Mediciones:
Realizar mediciones empíricas y analíticas para determinar la eficiencia de ambos algoritmos, incluyendo conteo de asignaciones, comparaciones, tiempo, memoria consumida, factor de crecimiento y clasificación en notación O.
### Consultas: 
Imprimir varios resultados, como la solución final, las mediciones, los cruces y mutaciones realizados, y las mejores poblaciones por tamaño.
### Documentación: 
Generar una documentación externa con portada, introducción, análisis del problema, solución, análisis de resultados, conclusiones, recomendaciones, literatura citada y bitácora. También se requiere una documentación interna con descripción de estructuras, funciones e instrucciones.

En resumen, el proyecto involucra implementar dos estrategias distintas para resolver el problema de la mochila, realizar mediciones de eficiencia y generar documentación detallada del proceso.