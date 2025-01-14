public class MangueraLlenado implements Runnable {
    private final Deposito deposito;
    private boolean vaciando;

    public MangueraLlenado(Deposito deposito) {
        this.deposito = deposito;
        this.vaciando = false; // Empieza llenando
    }

    @Override
    public void run() {
        int ritmo = 10; // Ritmo inicial de llenado
        while (!vaciando) {
            synchronized (deposito) {
                int nivel = deposito.getNivel();
                if (nivel >= 1000) {
                    System.out.println("Depósito lleno. Cambiando a vaciado.");
                    vaciando = true; 
                    deposito.notifyAll();
                } else if (nivel >= 900) {
                    ritmo = 10; 
                } else {
                    ritmo = 5; 
                }
                deposito.llenar(ritmo);
            }

            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
