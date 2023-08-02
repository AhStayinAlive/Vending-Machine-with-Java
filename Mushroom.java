import java.util.ArrayList;

public class Mushroom extends Item{
    public Mushroom() {
        super("Mushroom", 12, 22);
    }

    public void addItem() {
        this.stock.add(new Mushroom());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}