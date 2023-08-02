import java.util.ArrayList;

public class OnionRings extends Item{
    public OnionRings() {
        super("Onion Rings", 42, 411);
    }

    public void addItem() {
        this.stock.add(new OnionRings());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}