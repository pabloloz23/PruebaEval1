package com.dominio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Esta clase es responsable de leer el tablero de un fichero en forma de ceros y unos,
 * de generar el estado inicial de forma aleatoria mediante Montecarlo,
 * de transitar de estados aplicando las reglas del juego de la vida y de mostrar dichos estados.
 */
public class Tablero {

    private static final int DIMENSION = 30;
    private int[][] estadoActual;
    private int[][] estadoSiguiente;

    /**
     * Constructor de la clase Tablero.
     * Inicializa las matrices para el estado actual y el siguiente.
     */
    public Tablero() {
        estadoActual = new int[DIMENSION][DIMENSION];
        estadoSiguiente = new int[DIMENSION][DIMENSION];
    }

    /**
     * Lee el estado inicial desde un fichero llamado "matriz".
     * <p>
     * La secuencia de ceros y unos del fichero se guarda en 'estadoActual' y, utilizando las reglas del juego
     * de la vida, se generan los ceros y unos correspondientes en 'estadoSiguiente'.
     * Se asume que el fichero contiene 30 filas y 30 columnas.
     * </p>
     */
    public void leerEstadoActual() {
    try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/uax/dominio/matriz.txt"))) {
        for (int i = 0; i < DIMENSION; i++) {
            String linea = br.readLine();
            if (linea != null) {
                // Eliminar todos los espacios en blanco de la línea
                linea = linea.replaceAll("\\s+", "");
                for (int j = 0; j < DIMENSION; j++) {
                    estadoActual[i][j] = Character.getNumericValue(linea.charAt(j));
                }
            } else {
                // Si por alguna razón la línea es nula, se llena la fila de ceros.
                for (int j = 0; j < DIMENSION; j++) {
                    estadoActual[i][j] = 0;
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    // Una vez leído el estado actual, se calcula el estado siguiente.
    calcularEstadoSiguiente();
}

    /**
     * Genera un estado inicial aleatorio para el tablero.
     * <p>
     * Para cada celda se genera un número aleatorio en el intervalo [0, 1). Si el número es menor que 0,5,
     * la celda se considera viva (1); en caso contrario, se considera muerta (0). El estado generado se guarda
     * en 'estadoActual' y se calcula 'estadoSiguiente' aplicando las reglas del juego de la vida.
     * </p>
     */
    public void generarEstadoActualPorMontecarlo() {
        Random random = new Random();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                // Si el número aleatorio es menor que 0,5, la celda está viva.
                estadoActual[i][j] = (random.nextDouble() < 0.5) ? 1 : 0;
            }
        }
        // Calcular el estado siguiente basado en el estado actual generado.
        calcularEstadoSiguiente();
    }

    /**
     * Transita al estado siguiente según las reglas del juego de la vida.
     * <p>
     * La variable 'estadoActual' se actualiza copiando el contenido de 'estadoSiguiente' y,
     * a continuación, se recalcula 'estadoSiguiente' en base al nuevo estado actual.
     * </p>
     */
    public void transitarAlEstadoSiguiente() {
        // Actualizar estadoActual con el contenido de estadoSiguiente.
        copiarMatriz(estadoSiguiente, estadoActual);
        // Calcular el nuevo estadoSiguiente a partir del estadoActual actualizado.
        calcularEstadoSiguiente();
    }

    /**
     * Calcula el estado siguiente del tablero a partir de 'estadoActual', aplicando las reglas del juego de la vida:
     * <ul>
     *   <li>Si una célula está viva y tiene 2 o 3 vecinos vivos, permanece viva.</li>
     *   <li>Si una célula está muerta y tiene exactamente 3 vecinos vivos, pasa a estar viva.</li>
     *   <li>En cualquier otro caso, la célula muere o permanece muerta.</li>
     * </ul>
     */
    private void calcularEstadoSiguiente() {
        int[][] nuevoEstado = new int[DIMENSION][DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                int vecinosVivos = contarVecinosVivos(i, j);
                if (estadoActual[i][j] == 1) {
                    // Célula viva: sobrevive si tiene 2 o 3 vecinos vivos.
                    nuevoEstado[i][j] = (vecinosVivos == 2 || vecinosVivos == 3) ? 1 : 0;
                } else {
                    // Célula muerta: revive si tiene exactamente 3 vecinos vivos.
                    nuevoEstado[i][j] = (vecinosVivos == 3) ? 1 : 0;
                }
            }
        }
        estadoSiguiente = nuevoEstado;
    }

    /**
     * Cuenta el número de vecinos vivos para la célula en la posición (fila, columna).
     *
     * @param fila    La fila de la célula.
     * @param columna La columna de la célula.
     * @return El número de vecinos vivos.
     */
    private int contarVecinosVivos(int fila, int columna) {
        int contador = 0;
        // Recorrer las 8 posiciones vecinas.
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                // Evitar contar la propia célula.
                if (i == fila && j == columna) {
                    continue;
                }
                // Verificar límites del tablero.
                if (i >= 0 && i < DIMENSION && j >= 0 && j < DIMENSION) {
                    contador += estadoActual[i][j];
                }
            }
        }
        return contador;
    }

    /**
     * Copia el contenido de la matriz origen a la matriz destino.
     *
     * @param origen  La matriz de la que se copia el contenido.
     * @param destino La matriz donde se copia el contenido.
     */
    private void copiarMatriz(int[][] origen, int[][] destino) {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                destino[i][j] = origen[i][j];
            }
        }
    }

    /**
     * Devuelve una representación textual del estado actual del tablero.
     * <p>
     * Cada célula viva se representa con "x" y cada célula muerta con un espacio en blanco.
     * Cada fila se muestra en una línea separada.
     * </p>
     *
     * @return Una cadena que representa el estado actual del tablero.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                sb.append(estadoActual[i][j] == 1 ? "x" : " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

