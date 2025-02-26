package com.aplicacion;

import com.aplicacion.dominio.Tablero;
import com.aplicacion.mates.Matematicas;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        while (true) {
            while (opcion < 1 || opcion > 3) {
                System.out.println("Introduce una opción: ");
                System.out.println("1. Generar número PI");
                System.out.println("2. Simulación del juego de la vida");
                System.out.println("3. Salir");

                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    if (opcion < 1 || opcion > 3) {
                        System.out.println("Opción no válida. Por favor, introduce 1, 2 o 3.");
                    }
                } else {
                    System.out.println("Entrada no válida. Por favor, introduce un número.");
                    sc.next(); // Limpiar la entrada no válida
                }
            }

            if (opcion == 3) {
                System.out.println("Saliendo del programa...");
                break;
            }

            switch (opcion) {
                case 1:
                    System.out.println("El número PI es " + Matematicas.generarNumeroPi(Integer.parseInt(args[0])));
                    break;
                case 2:
                    try {
                        Tablero tablero = new Tablero();
                        System.out.println("SIMULACIÓN CON TABLERO LEÍDO ");
                        tablero.leerEstadoActual();
                        System.out.println(tablero);
                        for (int i = 0; i <= 5; i++) {
                            TimeUnit.SECONDS.sleep(1);
                            tablero.transitarAlEstadoSiguiente();
                            System.out.println(tablero);
                        }
                        System.out.println("SIMULACIÓN CON TABLERO GENERADO MEDIANTE MONTECARLO");
                        tablero.generarEstadoActualPorMontecarlo();
                        System.out.println(tablero);
                        for (int i = 0; i <= 5; i++) {
                            TimeUnit.SECONDS.sleep(1);
                            tablero.transitarAlEstadoSiguiente();
                            System.out.println(tablero);
                        }
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    break;
                default:
                    System.out.println("Opción no válida");
            }

            // Resetear la opción para volver a mostrar el menú
           if (opcion != 3) {
                opcion = -1;
            }
        }
    }
}