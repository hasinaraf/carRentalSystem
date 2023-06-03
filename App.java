import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for running the program
 */
public class App {

    // constants
    public static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public static final File ASSET_PATH = new File("assets/");

    // static variables
    private static App instance;

    // instance variables
    private List<Car> cars;
    private List<Customer> customers;
    private List<Transaction> transactions;

    private App() {
        cars = new ArrayList<Car>();
        customers = new ArrayList<Customer>();
        transactions = new ArrayList<Transaction>();
    }

    // getters
    public List<Car> getCars() {
        return cars;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    // setters
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    // addition methods
    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // delete methods
    public void deleteCar(int number) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getNumber() == number) {
                cars.remove(i);
            }
        }
    }

    public void deleteCustomer(String username) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getUserName().equals(username)) {
                customers.remove(i);
            }
        }
    }

    public void deleteTransaction(String id) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getId().equals(id)) {
                transactions.remove(i);
            }
        }
    }

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean toContinue = true;

        App app = App.getInstance();
        app.readCars("cars.csv");
        app.readCustomers("customers.csv");
        app.readTransactions("transactions.csv");

        // print header
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%              Car Rental System                %");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        while (toContinue) {

            System.out.println();

            // print menu
            System.out.println(" 1. Login as Admin");
            System.out.println(" 2. Login as Customer");
            System.out.println(" 3. Exit");

            // get user input
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            // check user input
            if (choice == 1) {
                // login as admin
                Admin admin = new Admin();
                if (!admin.login()) {
                    System.out.println("Invalid Credentials");
                } else {
                    admin.showMenu();
                }

            } else if (choice == 2) {
                // login as customer
                Customer customer = Customer.login();
                if (customer == null) {
                    System.out.println("Invalid Credentials");
                } else {
                    customer.showMenu();
                }
            } else if (choice == 3) {
                System.out.println("Saving the data before exiting...");

                // save data to files
                app.saveCars("cars.csv");
                app.saveCustomers("customers.csv");
                app.saveTransactions("transactions.csv");

                // exit
                System.out.println("Thank you for using the application!");
                toContinue = false;
            } else {
                // invalid input
                System.out.println("Invalid input!");
            }

            
        }
        scanner.close();
    }

    // file handling methods
    private void saveTransactions(String string) {
        File file = new File(ASSET_PATH, string);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("Id,Username,Car Number,Date,Price,Status\n");

            for (Transaction transaction : transactions) {
                writer.write(transaction.toString());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    private void saveCustomers(String string) {
        File f = new File(ASSET_PATH, string);
        try {
            FileWriter writer = new FileWriter(f);
            writer.write("Username,Password\n");
            for (Customer customer : customers) {
                writer.write(customer.getUserName() + "," + customer.getPassword() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCars(String string) {
        try {
            File file = new File(ASSET_PATH, string);
            FileWriter writer = new FileWriter(file);
            // write header
            writer.write("Number,Make,Color,Price,Company,Seats\n");
            
            for (Car car : cars) {
                writer.write(car.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    private void readTransactions(String filename) {
        File file = new File(ASSET_PATH, filename);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                try {
                    String[] tokens = line.split(",");
                    String id = tokens[0];
                    String username = tokens[1];
                    int carNumber = Integer.parseInt(tokens[2]);
                    Date date = sdf.parse(tokens[3]);
                    int price = Integer.parseInt(tokens[4]);
                    int status = Integer.parseInt(tokens[5]);

                    Transaction transaction = new Transaction(id, username, date, carNumber, price, status);
                    addTransaction(transaction);

                } catch (NumberFormatException | ParseException e) {
                    // e.printStackTrace();
                }
                line = br.readLine();
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    private void readCustomers(String filename) {
        File file = new File(ASSET_PATH, filename);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            line = br.readLine(); // ignore header
            while (line != null) {
                String[] tokens = line.split(",");
                String username = tokens[0];
                String password = tokens[1];
                Customer customer = new Customer(username, password);
                addCustomer(customer);

                line = br.readLine();
            }

            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    private void readCars(String filename) {
        // read cars from file
        File f = new File(ASSET_PATH, filename);
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            // ignore first line (header)
            line = br.readLine();
            while (line != null) {
                try {
                    String[] tokens = line.split(",");
                    int number = Integer.parseInt(tokens[0]);
                    int make = Integer.parseInt(tokens[1]);
                    String color = tokens[2];
                    int price = Integer.parseInt(tokens[3]);
                    String company = tokens[4];
                    int seats = Integer.parseInt(tokens[5]);

                    Car car = new Car(number, make, color, price, company, seats);
                    addCar(car);

                } catch (NumberFormatException ex) {

                }
                line = br.readLine();
            }

            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            System.out.println("Car File not found!");
        } catch (IOException e) {
            System.out.println("Error reading Car file!");
        }
    }


}