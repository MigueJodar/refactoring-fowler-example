package ubu.gii.dass.refactoring;

import java.util.*;

/**
 * Tema Refactorizaciones
 * * Clase Customer refactorizada para delegar el cálculo de importes y puntos.
 * Se han eliminado las dependencias directas con la lógica de precios (Feature Envy).
 * * @author M. Fowler y <A HREF="mailto:clopezno@ubu.es">Carlos López</A>
 * @version 1.2
 */
public class Customer {
	private String _name;
	private List<Rental> _rentals;

	public Customer(String name) {
		_name = name;
		_rentals = new ArrayList<Rental>();
	}

	public void addRental(Rental arg) {
		_rentals.add(arg);
	}

	public String getName() {
		return _name;
	}

	/**
	 * Genera el informe de alquileres en formato texto plano.
	 */
	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = _rentals.iterator();
		String result = "Rental Record for " + getName() + "\n";
		
		while (rentals.hasNext()) {
			Rental each = rentals.next();
			
			// DELEGACIÓN: Rental se encarga de calcular su propio importe y puntos
			double thisAmount = each.getCharge();
			frequentRenterPoints += each.getFrequentRenterPoints();

			// Mostrar figuras para este alquiler
			result += "\t" + each.getMovie().getTitle() + "\t"
					+ String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		
		// Añadir líneas de pie de página
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

	/**
	 * Requerimiento 1: Genera el informe de alquileres en formato HTML.
	 */
	public String htmlStatement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = _rentals.iterator();
		String result = "<H1>Rental Record for <EM>" + getName() + "</EM></H1><P>\n";
		
		while (rentals.hasNext()) {
			Rental each = rentals.next();
			
			// Reutilización de la lógica delegada (evita duplicar fórmulas)
			double thisAmount = each.getCharge(); 
			frequentRenterPoints += each.getFrequentRenterPoints();

			// Formato específico para HTML
			result += "\t" + each.getMovie().getTitle() + ": "
					+ String.valueOf(thisAmount) + "<BR>\n";
			totalAmount += thisAmount;
		}
		
		// Pie del informe en HTML
		result += "<P>Amount owed is <EM>" + String.valueOf(totalAmount) + "</EM></P>\n";
		result += "<P>You earned <EM>" + String.valueOf(frequentRenterPoints)
				+ "</EM> frequent renter points</P>";
		return result;
	}
}