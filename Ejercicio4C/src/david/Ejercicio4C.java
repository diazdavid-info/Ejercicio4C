package david;

public class Ejercicio4C {

	public static void main(String[] args) throws InterruptedException {
		int iteraciones = 10;
		DatosComunes dc = new DatosComunes();
		
		Productor productor = new Productor(dc, iteraciones);
		ConsumidorSuma suma = new ConsumidorSuma(dc, iteraciones);
		ConsumidorResta resta = new ConsumidorResta(dc, iteraciones);
		
		Thread t1, t2, t3;
		
		t1 = new Thread(productor, "add");
		t2 = new Thread(suma, "suma");
		t3 = new Thread(resta, "resta");
		
		t1.start();
		t2.start();
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
		
		System.out.println("Total Suma: "+dc.getTotalSuma());
		System.out.println("Total resta: "+dc.getTotalResta());

	}

}
