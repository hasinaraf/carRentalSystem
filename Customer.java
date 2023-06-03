import java.util.Date;
import java.util.List;

// package classes;

public class Customer {

    private String username, password;

    public Customer(String username2, String password2) {
        this.username = username2;
        this.password = password2;
    }

    // getters
    public String getUserName() {
        return username;
    }

    public String getPassword(){
        return password;
    }

    public static Customer login() {
        // read username and password
        System.out.print("Enter username: ");
        String username = System.console().readLine();
        System.out.print("Enter password: ");
        String password = System.console().readLine();

        // check if username and password are correct
        List<Customer> customers = App.getInstance().getCustomers();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getUserName().equals(username) && customers.get(i).getPassword().equals(password)) {
                return customers.get(i);
            }
        }
        return null;
    }

    private void showAllCars(){
        // show all cars
        List<Car> cars = App.getInstance().getCars();
        if (cars.size() == 0){
            System.out.println("No cars available");
        }
        else{
            for (Car car : cars) {
                System.out.println(car);
            }
        }
    }

    private void showAllPendingTransactions(){
        // show all pending transactions
        List<Transaction> transactions = App.getInstance().getTransactions();
        boolean printed = false;
        for (Transaction transaction : transactions) {
            if (transaction.getUsername().equals(this.username) && transaction.getStatus() == Transaction.PENDING) {
                System.out.println(transaction);
                printed = true;
            }
        }
        if (!printed){
            System.out.println("No pending transactions");
        }
    }

    private void bookCar(){
        // book a car
        System.out.println("Enter a unique transaction id: ");
        String id = System.console().readLine();
        System.out.println("Enter car number: ");
        int carNumber = Integer.parseInt(System.console().readLine());
        System.out.println("Enter price: ");
        int price = Integer.parseInt(System.console().readLine());
        Transaction transaction = new Transaction(id, this.username, new Date(), carNumber, price, Transaction.PENDING);

        App.getInstance().addTransaction(transaction);
    }


    public void showMenu() {
        boolean toContinue = true;
        while(toContinue){
            System.out.println();
            System.out.println(" 1. Show all cars");
            System.out.println(" 2. Show all my pending transactions");
            System.out.println(" 3. Book a car");
            System.out.println(" 4. Logout");

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(System.console().readLine());
            if(choice == 1){
                showAllCars();
            } else if(choice == 2){
                showAllPendingTransactions();
            } else if(choice == 3){
                bookCar();
            } else if(choice == 4){
                toContinue = false;
            }

        }
    }

    @Override
    public String toString() {
        return username+","+password;
    }
    
}
