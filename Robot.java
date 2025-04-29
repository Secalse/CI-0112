import java.util.Random;

public class Robot {
    private String nombre;
    private int puntosVida;
    private int ataque;
    private int defensa;

    // Constructor
    public Robot(String nombre, int puntosVida, int ataque, int defensa) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    // Método para atacar a otro robot
    public void atacar(Robot otroRobot) {
        int daño = this.ataque - otroRobot.getDefensa();
        if (daño < 0) {
            daño = 0; 
        }

        System.out.println(this.nombre + " ataca a " + otroRobot.getNombre() +
            " causando " + daño + " de daño (defensa aplicada).");

        otroRobot.setPuntosVida(otroRobot.getPuntosVida() - daño);
    }

    // Método que indica si el robot sigue vivo
    public boolean estaVivo() {
        return this.puntosVida > 0;
    }
}