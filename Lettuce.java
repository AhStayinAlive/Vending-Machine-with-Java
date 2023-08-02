import java.util.ArrayList;

public class Lettuce extends Item{

    public Lettuce(){
        super("Lettuce", 8, 1);
    }

    public void addItem() {
        this.stock.add(new Lettuce());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}
