import java.util.ArrayList;

public class Burger extends Item{

    public Burger() {
        super("Burger", 25, 54);
    }
    public void addItem() {
        this.stock.add(new Burger());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}
