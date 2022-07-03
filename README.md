
# Mutantes

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar
contra los X-Men.

Para eso te ha pedido crear un programa con un método o función con la siguiente firma (En
alguno de los siguiente lenguajes Java

``
 boolean isMutant(String[] dna); 
``

Este método se encuentra en la clase 
``
SecuenciaService.java
``

El método recibe como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN.

Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras
iguales​, de forma oblicua, horizontal o vertical.

Ejemplo (Caso mutante):

String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

## Nivel 1
Programa (en cualquier lenguaje de programación) que cumpla con el método pedido por
Magneto.

La validación sobre el  array se realiza en la clase 
``
ValidadorCadenaAdn.java
``

En esta clase tiene los siguientes métodos:

```ruby
validarSecuencia: método púbico que orquesta la evaluación de las secuencias de ADN.
buscarAd: busca las secuencias mutantes en el ADN
transponerMatrix: retorna la matrix transpuesta (se usa para las busquedas verticales)
diagonales: retorna las diagonales de una matrix.
```


## Nivel 2:

Se tiene una API REST, expuesta en [https://mutant-2022.herokuapp.com/api/mutant](https://mutant-2022.herokuapp.com/api/mutant), que expone el servicio donde se pueda detectar si un humano es mutante enviando la secuencia de ADN mediante un HTTP POST con un Json el cual tenga el siguiente formato:

``
POST → /mutant
{
“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
``

El servicio devuelve un HTTP 200-OK, en caso contrario un 403-Forbidden.

## Nivel 3:

Se expone el servicio  [https://mutant-2022.herokuapp.com/api/stats](https://mutant-2022.herokuapp.com/api/stats) que devuelve un Json con las estadísticas de las verificaciones de las secuencias de ADN:

``
ADN: {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}
``
