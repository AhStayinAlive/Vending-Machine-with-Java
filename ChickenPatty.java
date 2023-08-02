import java.util.ArrayList;

public class ChickenPatty extends Item{

    public ChickenPatty() {
        super("Chicken Patty", 25, 54);
    }
    public void addItem() {
        this.stock.add(new ChickenPatty());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}
