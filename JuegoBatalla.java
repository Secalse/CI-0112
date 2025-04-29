import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class JuegoBatalla {

    // Variables globales
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Robot> robots = new ArrayList<>();

    public static void main(String[] args) {
        // Menú principal del juego
        int opcion;       
        do {
            System.out.println("=================================");
            System.out.println("======== BATALLA DE ROBOTS ======");
            System.out.println("=================================");
            System.out.println(" 1. Crear robots");
            System.out.println(" 2. Iniciar batalla");
            System.out.println(" 3. Mostrar resultados");
            System.out.println(" 0. SALIR");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (opcion) {
                case 1:
                    crearRobots();
                    break;
                case 2:
                    iniciarBatalla();
                    break;
                case 3:
                    mostrarEstado();
                    mostrarGanador();
                    break;
                case 0:
                    System.out.println("Saliendo del juego");
                    break;
                default:
                    System.out.println("Ingrese una opción válida.");
            }
        } while (opcion != 0);

        scanner.close(); 
    }

    // Método para crear robots
    private static void crearRobots() {
        int cantidadRobots = 0; // variable para controlar la cantidad de robots del juego
        do {
            System.out.print("Ingrese la cantidad de robots (entre 2 y 10): ");
            cantidadRobots = scanner.nextInt();
            if (cantidadRobots < 2 || cantidadRobots > 10) {
                System.out.println("Cantidad inválida. Debe ser entre 2 y 10.");
            }
        } while (cantidadRobots < 2 || cantidadRobots > 10);

        scanner.nextLine(); 

        // Crear los robots
        robots.clear(); // esto limpia la lista anterior si había robots
        for (int i = 0; i < cantidadRobots; i++) {
            System.out.println("Robot " + (i + 1) + " ---");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Puntos de Vida (50-100): ");
            int puntosVida = validarDatosEntrada(50, 100);

            System.out.print("Ataque (10-20): ");
            int ataque = validarDatosEntrada(10, 20);

            System.out.print("Defensa (0-10): ");
            int defensa = validarDatosEntrada(0, 10);

            robots.add(new Robot(nombre, puntosVida, ataque, defensa));
        }
    }

    // Método para validar que los valores ingresados estén en el rango correcto
    private static int validarDatosEntrada(int min, int max) {
        int valor;
        do {
            valor = scanner.nextInt();
            if (valor < min || valor > max) {
                System.out.println("Valor inválido. Ingrese un número entre " + min + " y " + max + ": ");
            }
        } while (valor < min || valor > max);
        scanner.nextLine(); // limpiar el salto de línea pendiente
        return valor;
    }

     // Método de batallas entre robots
private static void iniciarBatalla() {
    Random random = new Random();
    boolean continuar = true;

    System.out.println("Comienza la batalla de robots");

    while (robots.size() > 1 && continuar) {
        // Mostrar el menú
        System.out.println("\n== Menú de batalla ==");
        System.out.println("1. Continuar");
        System.out.println("2. Mostrar estado");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        
        String input = scanner.nextLine(); // Leer como texto para evitar errores

        if (input.equals("1")) {
            // Copiar los robots vivos
            ArrayList<Robot> robotsVivos = new ArrayList<>();
            for (Robot r : robots) {
                if (r.estaVivo()) {
                    robotsVivos.add(r);
                }
            }

            // Cada robot ataca a otro
            for (Robot atacante : robotsVivos) {
                if (!atacante.estaVivo()) {
                    continue;
                }

                Robot objetivo;
                do {
                    objetivo = robots.get(random.nextInt(robots.size()));
                } while (objetivo == atacante || !objetivo.estaVivo());

                atacante.atacar(objetivo);

                if (!objetivo.estaVivo()) {
                    System.out.println("Robot " + objetivo.getNombre() + " ha sido eliminado.");
                }
            }

        } else if (input.equals("2")) {
            // Mostrar estado cuando el usuario quiera
            mostrarEstado();

        } else if (input.equals("0")) {
            // Salir de la batalla
            System.out.println("Salir de la Partida.");
            continuar = false;

        } else {
            // Opción no válida
            System.out.println("Opción no válida. Inténtalo de nuevo.");
        }

        // Revisar al final del ciclo si queda un ganador
        if (robotsVivos() == 1) {
            mostrarGanador();
            continuar = false; // Terminar la batalla
        } else if (robotsVivos() == 0 && continuar) {
            System.out.println("La batalla terminó sin ganadores.");
            continuar = false;
        }
    }
}

// Método de apoyo para contar robots vivos
private static int robotsVivos() {
    int vivos = 0;
    for (Robot r : robots) {
        if (r.estaVivo()) {
            vivos++;
        }
    }
    return vivos;
}
    

    // Método que muestra el estado actual de los robots
    private static void mostrarEstado() {
        System.out.println("== Estado actual de los robots ==");
        for (Robot robot : robots) {
            System.out.println(robot.getNombre() + " - Vida: " + robot.getPuntosVida() +
                               ", Ataque: " + robot.getAtaque() +
                               ", Defensa: " + robot.getDefensa());
        }
    }

    // Método que muestra el robot ganador
    private static void mostrarGanador() {
        ArrayList<Robot> robotsVivos = new ArrayList<>();
        for (Robot robot : robots) {
            if (robot.estaVivo()) {
                robotsVivos.add(robot);
            }
        }

        if (robotsVivos.size() == 1) {
            System.out.println("El Robot " + robotsVivos.get(0).getNombre() + " HA GANADO!");
        } else {
            System.out.println("No hubo un ganador.");
        }
    }
}
