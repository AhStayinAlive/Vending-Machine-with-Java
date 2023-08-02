import java.util.ArrayList;

public class BeefPatty extends Item{
    public BeefPatty() {
        super("Beef Patty", 31, 247);
    }

    public void addItem() {
        this.stock.add(new BeefPatty());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}