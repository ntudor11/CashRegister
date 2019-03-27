import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Item {

    private String barCode;
    private String category;
    private String itemName;
    private double price;
    private Discount disc;
    private boolean hasDiscount = false;

    public Item(String barCode, String category, String itemName, double price) {
        this.barCode = barCode;
        this.category = category;
        this.itemName = itemName;
        this.price = price;
    }

    public void setDisc(Discount disc) {
        this.disc = disc;
        hasDiscount = true;
    }

    public Discount getDisc() {
        return disc;
    }

    public boolean getHasDiscount() {
        return hasDiscount;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getCategory() {
        return category;
    }

    public String getItemName(){
        return itemName;
    }

    public double getPrice() {
        return price;
    }
}
