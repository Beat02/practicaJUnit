package practicaJUnit;

public class Entrada {
	private final int numSala;
    private int numButaca;
    private final double precio;

    public Entrada(int numSala, int numButaca, double precio) {
        this.numSala = numSala;
        this.numButaca = numButaca;
        this.precio = precio;
    }

    public int getNumSala() {
        return numSala;
    }

    public int getNumButaca() {
        return numButaca;
    }

    public void setNumButaca(int numButaca) {
        this.numButaca = numButaca;
    }

    public double getPrecio() {
        return precio;
    }

	@Override
	public String toString() {
		return "[Sala: " + numSala + "][Butaca: " + numButaca + "][Precio: " + precio + "]";
	}
    
    
}
