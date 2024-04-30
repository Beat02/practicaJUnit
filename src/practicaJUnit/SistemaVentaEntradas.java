package practicaJUnit;

import java.util.ArrayList;

public class SistemaVentaEntradas {
	private ArrayList<Entrada> entradas;
	private ArrayList<Sala> salas;
	public static final int MAX_SALAS = 5;
	public static final int MAX_BUTACAS_POR_SALA = 30;

	public SistemaVentaEntradas() {
		this.salas = new ArrayList<>();
		this.entradas = new ArrayList<>();
	}

	/**
	 * Añade una sala nueva
	 * @param numero
	 * @param pelicula
	 * @return
	 */
	public boolean anyadirSala(int numero, String pelicula) {
		
		Sala nuevaSala = new Sala(numero, pelicula);
		
		if (salas.contains(nuevaSala) || salas.size() >= MAX_SALAS) {
			System.out.println("La sala ya existe o se ha alcanzado el máximo número de salas");
			return false; // La sala ya existe o se ha alcanzado el máximo número de salas
		}
		salas.add(nuevaSala);
		return true;
	}

	public boolean comprarEntrada(int sala, int butaca) {
		
		if (!existeSala(sala)) {
			System.out.println("La sala no existe");
			return false; // La sala no existe
		}
		if (butaca <= 0 || butaca > MAX_BUTACAS_POR_SALA) {
			System.out.println("Número de butaca inválido");
			return false; // Número de butaca inválido
		}
		Entrada nuevaEntrada = new Entrada(sala, butaca, calcularPrecioEntrada(butaca));
		return entradas.add(nuevaEntrada);
	}
	
	private boolean existeSala(int numSala) {
		Sala salaBusqueda = new Sala(numSala, null);
		return this.salas.contains(salaBusqueda);
		
	}

	public int getEntradasVendidasPorSala(int numero) {
		int total = 0;
		for (Entrada entrada : entradas) {
			if (entrada.getNumSala() == numero) {
				total++;
			}
		}
		return total;
	}

	public void mostrarCartelera() {
		for (Sala sala : salas) {
			System.out.println(sala);
		}
	}
	
	public void mostrarEntradas(){
		for (Entrada entrada : entradas) {
			System.out.println(entrada);
		}
	}

	public double totalRecaudacion() {
		double total = 0.0;
		for (Entrada entrada : entradas) {
			total += entrada.getPrecio();
		}
		return total;
	}

	public double calcularPrecioEntrada(int numButaca) {
		if (numButaca <= 10) {
			return 10.0;
		} else if (numButaca <= 20) {
			return 8.0;
		} else {
			return 5.0;
		}
	}

	// AÑADIDO POR PROFESOR //
	public ArrayList<Entrada> getEntradas() {
		return entradas;
	}



	public ArrayList<Sala> getSalas() {
		return salas;
	}

	public void vaciarCine(){
		getSalas().clear();
		getEntradas().clear();
	}


}
