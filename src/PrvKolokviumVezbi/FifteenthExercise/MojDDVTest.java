package PrvKolokviumVezbi.FifteenthExercise;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

enum ITEM_MAX_TYPE {
    A,
    B,
    V,
}

class ProductPrice {
    private double productPrice;
    private ITEM_MAX_TYPE itemMaxType;

    public static double A_TYPE = 0.18;
    public static double B_TYPE = 0.05;
    public static double V_TYPE = 0.00;

    public static double DDV = 0.15;

    public ProductPrice(double productPrice, ITEM_MAX_TYPE itemMaxType) {
        this.productPrice = productPrice;
        this.itemMaxType = itemMaxType;
    }

    public ITEM_MAX_TYPE getItemMaxType() {
        return itemMaxType;
    }

    public double getProductPrice() {
        return productPrice;
    }
}

class FiskalnaSmetka {
    private long id;
    private ArrayList<ProductPrice> productPrices;


    public FiskalnaSmetka(String line) {
        productPrices = new ArrayList<>();
        String[] splitedLine = line.split("\\s+");
        ArrayList<String> stringArrayList = (ArrayList<String>) Arrays.stream(splitedLine).collect(Collectors.toList());
        this.id = Long.parseLong(stringArrayList.remove(0));
        for (int i = 0; i < stringArrayList.size(); i += 2) {
            if (stringArrayList.get(i + 1).compareTo("A") == 0) {
                productPrices.add(new ProductPrice(Long.parseLong(stringArrayList.get(i)), ITEM_MAX_TYPE.A));
            } else if (stringArrayList.get(i + 1).compareTo("B") == 0) {
                productPrices.add(new ProductPrice(Long.parseLong(stringArrayList.get(i)), ITEM_MAX_TYPE.B));
            } else {
                productPrices.add(new ProductPrice(Long.parseLong(stringArrayList.get(i)), ITEM_MAX_TYPE.V));
            }
        }
        if(amountSum() > 30000)
        {
            throw new AmountNotAllowedException(String.format("Receipt with amount %f is not allowed to be scanned", amountSum()));
        }
    }

    public double amountSum()
    {
        return productPrices.stream().mapToDouble(ProductPrice::getProductPrice).sum();
    }

    public long getId() {
        return id;
    }

    public double taxReturn()
    {
        double taxSum = 0;
        for(ProductPrice item : productPrices)
        {

            if(item.getItemMaxType().equals(ITEM_MAX_TYPE.A))
            {
                taxSum += item.getProductPrice() * ProductPrice.A_TYPE * ProductPrice.DDV;
            }
            else if(item.getItemMaxType().equals(ITEM_MAX_TYPE.B))
            {
                taxSum += item.getProductPrice() * ProductPrice.B_TYPE * ProductPrice.DDV;
            }
            else
            {
                taxSum += item.getProductPrice() * ProductPrice.V_TYPE * ProductPrice.DDV;
            }
        }
        return taxSum;
    }

    public ArrayList<ProductPrice> getProductPrices() {
        return productPrices;
    }

    @Override
    public String toString() {
        return String.format("%d %.2f %.2f\n", id, amountSum(), taxReturn());
    }
}


class MojDDV {
    ArrayList<FiskalnaSmetka> fiskalniSmetkiList = new ArrayList<>();


    void readRecords(InputStream inputStream) throws AmountNotAllowedException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        bufferedReader.lines().forEach(line ->{
            try
            {
                FiskalnaSmetka fiskalnaSmetka = new FiskalnaSmetka(line);
                this.fiskalniSmetkiList.add(fiskalnaSmetka);
            }
            catch (AmountNotAllowedException e)
            {
                System.out.println(e.getMessage());
            }
        });
    }

    void printTaxReturns (OutputStream outputStream)
    {
        PrintWriter printWriter = new PrintWriter(outputStream);
        fiskalniSmetkiList.forEach(r->printWriter.print(r.toString()));
        printWriter.flush();
        printWriter.close();
    }
}

class AmountNotAllowedException extends RuntimeException
{
    public AmountNotAllowedException(String message) {
        super(message);
    }
}

public class MojDDVTest {

    public static void main(String[] args) throws FileNotFoundException {

        MojDDV mojDDV = new MojDDV();
        File file = new File("G:\\NaprednoProgramiranjeZadaci\\Napredno_Programiranje\\src\\PrvKolokviumVezbi\\FifteenthExercise\\file.txt");
        System.out.println("===READING RECORDS FROM INPUT STREAM===");
//        mojDDV.readRecords(System.in);
        mojDDV.readRecords(new FileInputStream(file));

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);

    }
}