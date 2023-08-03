import java.util.ArrayList;

public class Onions extends Item{
    private static ArrayList<Item> stock = new ArrayList<>();
    public Onions() {
        super("Onions", 0, 15);
        Onions.stock.add(this);
    }

    public void addItem() {
        new Onions();
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}