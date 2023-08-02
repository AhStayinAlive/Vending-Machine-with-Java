import java.util.ArrayList;

public class Bread extends Item{
    public Bread() {
        super("Bread", 0, 120);
    }

    public void addItem() {
        this.stock.add(new Bread());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}
