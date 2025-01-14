public class Desague implements Runnable {
    private final Deposito deposito;
    private boolean vaciando;

    public Desague(Deposito deposito) {
        this.deposito = deposito;
        this.vaciando = false; // Comienza apagado
    }

    @Override
    public void run() {
        int ritmo = 0; // Ritmo inicial de vaciado
        while (true) {
            synchronized (deposito) {
                int nivel = deposito.getNivel();
                if (nivel >= 900 && !vaciando) {
                    System.out.println("Activando vaciado.");
                    vaciando = true; // Cambia al estado de vaciado
                }

                if (vaciando && nivel <= 0) {
                    System.out.println("Depósito vacío. Cambiando a llenado.");
                    vaciando = false; // Cambia al estado de llenado
                    deposito.notifyAll();
                } else if (vaciando && nivel >= 1000) {
                    ritmo = 10; 
                } else if (vaciando && nivel >= 900) {
                    ritmo = 5; 
                } else if (!vaciando) {
                    ritmo = 0; // No vacía porque está a false
                }

                if (ritmo > 0) {
                    deposito.vaciar(ritmo);
                }
            }

            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
