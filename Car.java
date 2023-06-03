// package classes;

public class Car {

    private int number;
    private int make;
    private String color;
    private int price;
    private String company;
    private int seats;

    public Car(int number, int make, String color, int price, String company, int seats) {
        this.number = number;
        this.make = make;
        this.color = color;
        this.price = price;
        this.company = company;
        this.seats = seats;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number + "," + make + "," + color + "," + price + "," + company + "," + seats;
    }

}
