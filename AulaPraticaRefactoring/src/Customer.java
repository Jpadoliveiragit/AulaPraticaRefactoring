public class Customer {

    private String name;
    private Rental[] rentals = new Rental[10];
    private int numRentals = 0;

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals[numRentals++] = rental;
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (int i = 0; i < numRentals; i++) {
            Rental rental = rentals[i];

            double thisAmount = calculateAmount(rental);

            frequentRenterPoints += calculateFrequentRenterPoints(rental);

            result.append("\t").append(rental.getMovie().getTitle())
                  .append("\t").append(thisAmount).append("\n");

            totalAmount += thisAmount;
        }

        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

    private double calculateAmount(Rental rental) {
        double thisAmount = 0;

        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (rental.getDaysRented() > 2)
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                break;

            case Movie.NEW_RELEASE:
                thisAmount += rental.getDaysRented() * 3;
                break;

            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3)
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    private int calculateFrequentRenterPoints(Rental rental) {
        if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE &&
            rental.getDaysRented() > 1)
            return 2;

        return 1;
    }
}
