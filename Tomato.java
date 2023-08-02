import java.util.ArrayList;

public class Tomato extends Item{

    public Tomato() {
        super("Tomato", 7, 3);
    }

    public void addItem() {
        this.stock.add(new Tomato());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}
