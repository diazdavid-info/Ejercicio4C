package david;

import java.util.Random;

public class DatosComunes {
	private int[] arg;
	private Random random;
	volatile private int suma;
	volatile private int resta;
	private int totalSuma;
	private int totalResta;
	
	public DatosComunes(){
		arg = new int[10];
		random = new Random();
		suma = 0;
		resta = 0;
		totalSuma = 0;
		totalResta = 0;
	}
	
	synchronized public void accion(int posicion) throws InterruptedException{
		if(Thread.currentThread().getName().equals("add")){
			while(Thread.interrupted() && (suma != 0 || resta != 0)){
				System.out.println("duermo add");
				wait();
				System.out.println("Despierto add");
			}
			System.out.println("Añado número: "+posicion);
			arg[posicion] = random.nextInt(10000);
			Thread.currentThread().interrupt();
			suma++;
			resta++;
			notifyAll();
		}else if (Thread.currentThread().getName().equals("suma")) {
			while(Thread.interrupted() || suma == 0){
				System.out.println("duermo suma");
				wait();
				System.out.println("despietto suma");
			}
			System.out.println("SUMA: "+posicion+" / "+arg[posicion]);
			totalSuma += arg[posicion];
			suma--;
			Thread.currentThread().interrupt();
			notifyAll();
		}else if (Thread.currentThread().getName().equals("resta")) {
			while(Thread.interrupted() || resta == 0){
				System.out.println("duermo resta");
				wait();
				System.out.println("despietrto resta");
			}
			System.out.println("RESTA: "+posicion+" / "+arg[posicion]);
			totalResta -= arg[posicion];
			resta--;
			Thread.currentThread().interrupt();
			notifyAll();
		}
	}
	
	public int getTotalSuma(){
		return totalSuma;
	}
	
	public int getTotalResta(){
		return totalResta;
	}

}
