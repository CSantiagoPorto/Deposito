public class Entrada {
    public static void main(String[] args) {
    	
    	
    	/*
Un depósito de agua es llenado a un ritmo de 10 litros por segundo.
Cuando ese depósito llega a los 900 litros, se activa un proceso de vaciado a un ritmo de 5 litros por segundo, mientras se sigue llenando.
Cuando el depósito llega a los 1000 litros, el proceso de llenado se para y se aumenta el de vaciado a 10 litros por segundo.
Cuando el depósito llega a los 100 litros de vaciado, se activa un proceso de llenado a un ritmo de 5 litros por segundo, mientras se sigue vaciando.
Cuando el depósito llega a los 0 litros, el proceso de vaciado se para y se aumenta el de llenado a 10 litros por segundo.
Simula con monitores dichos procesos y ve representando en pantalla las acciones que se van tomando. */
        Deposito deposito = new Deposito(1000); 

        MangueraLlenado manguera = new MangueraLlenado(deposito);
        Desague desague = new Desague(deposito);

        Thread hiloLlenador = new Thread(manguera, "Llenador");
        Thread hiloVaciador = new Thread(desague, "Vaciador");

        hiloLlenador.start();
        hiloVaciador.start();
       /* deposito.llenar(5);
        System.out.println("Litros"+ deposito.getNivel());
        deposito.vaciar(0);
        System.out.println("Litros"+ deposito.getNivel());
        deposito.llenar(10);
        System.out.println("Litros"+ deposito.getNivel());
        deposito.vaciar(5);
        System.out.println("Litros"+ deposito.getNivel());
        deposito.vaciar(100);
        System.out.println("Litros"+ deposito.getNivel());*/
    }
}
