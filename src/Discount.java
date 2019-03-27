import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Discount {
    private String barCode;
    private int itemCount;
    private double discountValue;

    public Discount(String barCode, int itemCount, Double discountValue) {
        this.barCode = barCode;
        this.itemCount = itemCount;
        this.discountValue = discountValue;
    }


}
