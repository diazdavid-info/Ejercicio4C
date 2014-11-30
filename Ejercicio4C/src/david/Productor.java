package david;

public class Productor implements Runnable{

	DatosComunes dc;
	int iteraciones;
	
	public Productor(DatosComunes dc, int iteraciones){
		this.dc = dc;
		this.iteraciones = iteraciones;
	}
	
	public void run() {
		for (int i = 0; i < iteraciones; i++) {
			try {
				dc.accion(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
