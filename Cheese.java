import java.util.ArrayList;

public class Cheese extends Item{

    public Cheese() {
        super("Cheese", 10, 28);
    }
    
    public void addItem() {
        this.stock.add(new Cheese());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}
