import java.io.*;
import java.util.*;

public class CashRegister /*implements Comparator<Item>*/{

    private HashMap<String, Item> priceMap = new HashMap<>();

    private HashMap<Item, Integer> occurrenceCount = new HashMap<>();  // for counting discounts

    public CashRegister (String priceFilename, String discountsFilename) {
        addPrices(priceFilename);
        addDiscount(discountsFilename);
    }

    public void addPrices(String priceFilename) {
        try {
            File file = new File(priceFilename);
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] splitLine = line.split(",");

                String barCode = splitLine[0];
                String category = splitLine[1];
                String itemName = splitLine[2];
                Double price = Double.parseDouble(splitLine[3]);

                Item item = new Item(barCode, category, itemName, price);
                priceMap.put(barCode, item);
            }
        } catch(IOException e) {
            System.out.println("Cannot find price file. " + e);
        }
    }

    public void addDiscount(String discountsFilename) {
        try {
            File file = new File(discountsFilename);
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] splitLine = line.split(",");

                String barCode = splitLine[0];
                Integer itemCount = Integer.parseInt(splitLine[1]);
                Double discountValue = Double.parseDouble(splitLine[2]);

                Discount discount = new Discount(barCode, itemCount, discountValue);
                Item item = priceMap.get(barCode);

                item.setDisc(discount);

            }
        } catch(IOException e) {
            System.out.println("Cannot find discounts file. " + e);
        }
    }

    public HashMap<Item, Integer> getBarCodes(String barcodeFilename) {

        try(Scanner scanner = new Scanner(new File(barcodeFilename))) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Item item = priceMap.get(line);

                if(!occurrenceCount.containsKey(item)) {
                    occurrenceCount.put(item, 1);
                } else {
                    int itemCount = occurrenceCount.get(item)+1;
                    occurrenceCount.put(item, itemCount);
                }
            }
        } catch(IOException e) {
            System.out.println("Couldn't find file! " + e);
        }

        return occurrenceCount;
    }


    public List<Item> getItemList(String barcodeFilename) {

        List<Item> itemList = new ArrayList<>();

        try(Scanner scanner = new Scanner(new File(barcodeFilename))) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Item item = priceMap.get(line);
                itemList.add(item);
            }

        } catch(IOException e) {
            System.out.println("Couldn't find file! " + e);
        }

        return itemList;
    }

    /*public void sortPrices() {
        String barcode = new String;
        Item toBeSorted = priceMap.get(barcode);

    }

    @Override
    public int compare(Item itemBefore, Item itemAfter) {
        if()
    }*/

    public void printReceipt(String barcodeFilename) {

        Map<Item, Integer> barCodes = getBarCodes(barcodeFilename);


        // List<String> categories = getItemList();

        /* for (Item barCode : barCodes.get()) {

            Item item = priceMap.get(barCode);

            if(item.getHasDiscount()) {
                System.out.println(" Discounted");
            }


            System.out.println("* " + item.getCategory() + " *");

            System.out.println( item.getItemName() + " " +  occurrenceCount.get(barCode) + " x " + item.getPrice() + " ");
            System.out.println();
        }*/
    }

    /*public static void main(String[] args) {
        CashRegister cashRegister = new CashRegister("prices.txt", "discounts.txt");

        cashRegister.addPrices("prices.txt");
        cashRegister.addDiscount("discounts.txt");

        System.out.println("Works");

    }*/
}