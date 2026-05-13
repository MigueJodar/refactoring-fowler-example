package ubu.gii.dass.refactoring;

public abstract class Statement {

	abstract String header(String name);

	abstract String rentalStatement(String title, double charge);

	abstract String footer(double totalAmount, int frequentRenterPoints);

	public static class HtmlStatement extends Statement {

		@Override
		String header(String name) {
			return "<H1>Rental Record for " + name + "</H1>\n";
		}

		@Override
		String rentalStatement(String title, double charge) {
			return "\t" + title + " : " + String.valueOf(charge) + "<BR>\\n";
		}

		@Override
		String footer(double totalAmount, int frequentRenterPoints) {
			return "<P>Amount owed is <EM>" + String.valueOf(totalAmount) + "</EM></p>\n" + "<P>You earned <EM>"
					+ String.valueOf(frequentRenterPoints) + "</EM> frequent renter points </P>";
		}

	}

	public static class TextStatement extends Statement {

		@Override
		String header(String name) {
			return "Rental Record for " + name + "\n";
		}

		@Override
		String rentalStatement(String title, double charge) {
			return "\t" + title + "\t" + String.valueOf(charge) + "\n";
		}

		@Override
		String footer(double totalAmount, int frequentRenterPoints) {
			return "Amount owed is " + String.valueOf(totalAmount) + "\n" + "You earned "
					+ String.valueOf(frequentRenterPoints) + " frequent renter points";
		}

	}
}