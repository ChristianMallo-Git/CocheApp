import java.util.Scanner;

public class Presentacion {

    public static final Scanner consola = new Scanner(System.in);
    public static String opcionElegida = "";
    public static int opcionCombustible;

    public static void main(String[] args) {

        System.out.println("***Bienvenido a la Aplicación de Coches***\n");
        try {
            Coche miCoche = crearCoche();
            System.out.println("\nVehículo añadido!\n");

            do {
                miCoche.mostrardetalles();
                mostrarMenuGeneral();
                opcionElegida = consola.nextLine();
                gestionarOpciones(opcionElegida, miCoche);
            } while (!opcionElegida.equalsIgnoreCase("end"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Coche crearCoche() {
        String marca = "";
        String modelo = "";

        boolean marcaValida = false;
        do {
            try {
                System.out.println("Introduce la marca del coche: ");
                marca = consola.nextLine();
                if (esNumero(marca)) {
                    throw new IllegalArgumentException("Dato incorrecto: La marca no puede ser un número. Por favor, inténtalo de nuevo. \n");
                }
                marcaValida = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!marcaValida);

        boolean modeloValido = false;
        do {
            try {
                System.out.println("Introduce el modelo del coche: ");
                modelo = consola.nextLine();
                if (esNumero(modelo)) {
                    throw new IllegalArgumentException("Dato incorrecto: El modelo no puede ser un número. Por favor, inténtalo de nuevo. \n");
                }
                modeloValido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!modeloValido);


        String tipocombustible = "";
        mostrarMenuCombustible();
        do {
            opcionCombustible = consola.nextInt();
            consola.nextLine();

            switch (opcionCombustible) {
                case 1:
                    tipocombustible = "Gasolina";
                    break;
                case 2:
                    tipocombustible = "Diésel";
                    break;
                case 3:
                    tipocombustible = "Híbrido";
                    break;
                case 4:
                    tipocombustible = "Eléctrico";
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elige un número entre 1 y 4.");
                    System.out.println("¿Qué número deseas elegir?:");
            }
        } while (opcionCombustible < 1 || opcionCombustible > 4);

        Coche miCoche = new Coche(marca, modelo, tipocombustible);

        return miCoche;
    }

    public static boolean esNumero(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void mostrarMenuCombustible() {
        System.out.println("""
                ¿Qué tipo de combustible utiliza el vehículo?
                 1.Gasolina
                 2.Diésel
                 3.Híbrido
                 4.Eléctrico
                 """);
    }

    private static void gestionarOpcionCombustible(int opcionCombustible) {


    }

    private static void mostrarMenuGeneral() {
        System.out.println("""
                ***ACCIONES A REALIZAR CON EL VEHÍCULO***
                '+' Acelerar
                '-' Frenar
                'n' Poner marcha neutra
                'r' Poner marcha atrás
                'd' Girar a la derecha
                'i' Girar a la izquierda
                'end' Termina el programa
                ¿Qué desea hacer?:
                """);
    }

    private static void gestionarOpciones(String opcionElegida, Coche miCoche) {

        switch (opcionElegida) {
            case "+":
                miCoche.acelerar();
                break;
            case "-":
                miCoche.frenar();
                break;
            case "r":
                miCoche.cambiarMarchaAtras(true);
                break;
            case "n":
                miCoche.cambiarMarchaAtras(false);
                break;
            case "d":
                miCoche.giroRueda(5);
                break;
            case "i":
                miCoche.giroRueda(-5);
                break;
            case "end":
                System.out.println("El vehículo ha sido apagado. Esperamos verle pronto de nuevo. ;)\n");
                break;
            default:
                System.out.println("No existe esa opción.\n");
                break;
        }
    }
}