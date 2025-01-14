public class Deposito {
    private int nivel = 0; 
    private final int capacidadMaxima;

    public Deposito(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public synchronized void llenar(int litros) {
        while (nivel >= capacidadMaxima) {
            try {
                System.out.println("El depósito está lleno. Esperando vaciado...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        nivel += litros;
        System.out.println("Llenando: +" + litros + " litros. Nivel actual: " + nivel);
        notify();
    }

    public synchronized void vaciar(int litros) {
        while (nivel <= 0) {
            try {
                System.out.println("El depósito está vacío. Esperando llenado...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        nivel -= litros;
        System.out.println("Vaciando: -" + litros + " litros. Nivel actual: " + nivel);
        notify();
    }

    public synchronized int getNivel() {
        return nivel;
    }
    public synchronized int setNivel() {
    	return nivel;
    }
}
