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
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (int i = 0; i < numRentals; i++) {
            Rental rental = rentals[i];

            result.append("\t")
                .append(rental.getMovie().getTitle())
                .append("\t")
                .append(calculateAmount(rental))
                .append("\n");
        }

        result.append("Amount owed is ").append(getTotalAmount()).append("\n");
        result.append("You earned ").append(getTotalFrequentRenterPoints()).append(" frequent renter points");

        return result.toString();
    }

    private double getTotalAmount() {
        double total = 0;
        for (int i = 0; i < numRentals; i++) {
            total += calculateAmount(rentals[i]);
        }
        return total;
    }

    private int getTotalFrequentRenterPoints() {
        int points = 0;
        for (int i = 0; i < numRentals; i++) {
            points += calculateFrequentRenterPoints(rentals[i]);
        }
        return points;
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

