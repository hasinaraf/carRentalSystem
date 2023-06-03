
import java.util.Date;

public class Transaction {

    public static final int PENDING = 0;
    public static final int COMPLETED = 1;

    private String id;
    private String username;
    private Date date;
    private int carNumber;
    private int price;
    private int status;

    public Transaction(String id, String username, Date date, int carNumber, int price, int status) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.carNumber = carNumber;
        this.price = price;
        this.status = status;
    }
    

    public String getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    @Override
    public String toString() {

        return id + "," + username + "," + carNumber + "," + App.sdf.format(date) + "," + price + "," + status;
    }


}
