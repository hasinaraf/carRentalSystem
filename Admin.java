import java.util.List;

public class Admin {

    public boolean login() {
        System.out.print("Enter username: ");
        String username = System.console().readLine();
        System.out.print("Enter password: ");
        String password = System.console().readLine();
        if (username.equals("admin") && password.equals("admin")) {
            return true;
        }
        return false;
    }

    private void addCar(){
        // input car data
        System.out.println("Enter car number: ");
        int number = Integer.parseInt(System.console().readLine());
        System.out.println("Enter car manufacturing year: ");
        int make = Integer.parseInt(System.console().readLine());
        System.out.println("Enter car color: ");
        String color = System.console().readLine();
        System.out.println("Enter car price: ");
        int price = Integer.parseInt(System.console().readLine());
        System.out.println("Enter car company: ");
        String company = System.console().readLine();
        System.out.println("Enter number of seats in the car: ");
        int seats = Integer.parseInt(System.console().readLine());
        Car car = new Car(number, make, color, price, company, seats);

        App.getInstance().addCar(car);
    }
    private void removeCar(){
        System.out.println("Enter car number: ");
        int number = Integer.parseInt(System.console().readLine());
        App.getInstance().deleteCar(number);
    }

    private void showAllCars(){
        List<Car> cars = App.getInstance().getCars();
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private void addCustomer(){
        System.out.print("Enter username: ");
        String username = System.console().readLine();
        System.out.print("Enter password: ");
        String password = System.console().readLine();
        Customer customer = new Customer(username, password);
        App.getInstance().addCustomer(customer);
    }

    private void removeCustomer(){
        System.out.println("Enter username: ");
        String username = System.console().readLine();
        App.getInstance().deleteCustomer(username);
    }

    private void showAllCustomers(){
        List<Customer> customers = App.getInstance().getCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private void showAllTransactions(){
        List<Transaction> transactions = App.getInstance().getTransactions();
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private void showAllPendingTransactions(){
        List<Transaction> transactions = App.getInstance().getTransactions();
        for (Transaction transaction : transactions) {
            if (transaction.getStatus() == Transaction.PENDING) {
                System.out.println(transaction);
            }
        }
    }

    private void approveTransaction(){
        showAllPendingTransactions();
        System.out.print("Enter transaction id to approve: ");
        String id = System.console().readLine();

        List<Transaction> transactions = App.getInstance().getTransactions();
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(id)) {
                transaction.setStatus(Transaction.COMPLETED);
            }
        }
        
    }

    public void showMenu() {
        boolean toContinue = true;

        while (toContinue) {
            System.out.println();
            System.out.println(" 1. Add Car");
            System.out.println(" 2. Remove Car");
            System.out.println(" 3. Add Customer");
            System.out.println(" 4. Remove Customer");
            System.out.println(" 5. Show All Cars");
            System.out.println(" 6. Show All Customers");
            System.out.println(" 7. Show All Transactions");
            System.out.println(" 8. Show All pending Transactions");
            System.out.println(" 9. Approve Transaction");
            System.out.println(" 10. Logout");

            // read input
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(System.console().readLine());

            if (choice == 1){
                // add car
                addCar();
            } else if (choice == 2){
                // remove car
                removeCar();
            } else if (choice == 3){
                // add customer
                addCustomer();
            } else if (choice == 4){
                // remove customer
                removeCustomer();
            } else if (choice == 5){
                // show all cars
                showAllCars();
            } else if (choice == 6){
                // show all customers
                showAllCustomers();
            } else if (choice == 7){
                // show all transactions
                showAllTransactions();
            } else if (choice == 8){
                // show all pending transactions
                showAllPendingTransactions();
            } else if (choice == 9){
                // approve transaction
                approveTransaction();
            } else if (choice == 10){
                // logout
                toContinue = false;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

}
