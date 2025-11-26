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
                .append(rental.getMovie().getAmount(rental.getDaysRented()))
                .append("\n");
        }

        result.append("Amount owed is ").append(getTotalAmount()).append("\n");
        result.append("You earned ").append(getTotalFrequentRenterPoints()).append(" frequent renter points");

        return result.toString();
    }

    private double getTotalAmount() {
        double total = 0;
        for (int i = 0; i < numRentals; i++) {
            total += rentals[i].getMovie().getAmount(rentals[i].getDaysRented());
        }
        return total;
    }

    private int getTotalFrequentRenterPoints() {
        int points = 0;
        for (int i = 0; i < numRentals; i++) {
            points += rentals[i].getMovie().getFrequentRenterPoints(rentals[i].getDaysRented());
        }
        return points;
    }
}
