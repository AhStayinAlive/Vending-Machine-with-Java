import java.util.ArrayList;

public class Chicken extends Item{
    private static ArrayList<Item> stock = new ArrayList<>();

    public Chicken() {
        super("Chicken", 25, 54);
        Chicken.stock.add(this);
    }
    public void addItem() {
        new Chicken();
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}
