package com.uax.Practica1;

import java.util.Random;

/**
 * La clase Matematicas contiene métodos matemáticos,
 * en este caso la generación de una aproximación al número PI
 * mediante el método Montecarlo.
 */
public class Matematicas {

    /**
     * Genera una aproximación al número PI mediante el método de Montecarlo.
     * <p>
     * El método genera un número de puntos aleatorios en el cuadrado definido
     * por [-1,1] x [-1,1] y cuenta cuántos de ellos caen dentro del círculo unitario.
     * La aproximación de PI se calcula usando la fórmula:
     * <br><br>
     * PI ≈ áreaCuadrado · (aciertos / pasos)
     * <br><br>
     * donde el área del cuadrado es 4 (lado 2) y "aciertos" es el número de puntos
     * dentro del círculo.
     * </p>
     *
     * @param pasos El número de puntos a generar para la simulación.
     * @return Una aproximación del valor de PI.
     */
    public static double generarNumeroPi(long pasos) {
        long aciertos = 0;         // Contador de puntos dentro del círculo
        double areaCuadrado = 4.0;  // Área del cuadrado [-1,1] x [-1,1]
        Random random = new Random();

        // Generamos 'pasos' puntos aleatorios
        for (long i = 0; i < pasos; i++) {
            // Generar un número aleatorio entre -1 y 1 para x y para y
            double x = -1 + 2 * random.nextDouble();
            double y = -1 + 2 * random.nextDouble();

            // Verificar si el punto (x, y) está dentro del círculo unitario
            // La condición es: x^2 + y^2 <= 1
            if (x * x + y * y <= 1) {
                aciertos++;
            }
        }

        // Retornar la aproximación de PI
        return areaCuadrado * ((double) aciertos / pasos);
    }
}