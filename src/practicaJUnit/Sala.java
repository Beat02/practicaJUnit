package practicaJUnit;

import java.util.Objects;

public class Sala {

	private final int numSala;
	private final String pelicula;

	public Sala(int numSala, String pelicula) {
		this.numSala = numSala;
		this.pelicula = pelicula;
	}

	public int getNumSala() {
		return numSala;
	}

	public String getPelicula() {
		return pelicula;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numSala);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		return numSala == other.numSala;
	}

	@Override
	public String toString() {
		return " [Sala " + numSala + "] " + pelicula;
	}
	
	
	
}
