import java.util.ArrayList;

public class Ketchup extends Item{
    public Ketchup() {
        super("Ketchup", 0, 10);
    }

    public void addItem() {
        this.stock.add(new Ketchup());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}