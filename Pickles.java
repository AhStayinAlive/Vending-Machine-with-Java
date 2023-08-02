import java.util.ArrayList;

public class Pickles extends Item{

    public Pickles(){
        super("Pickles", 8, 0.05);
    }

    public void addItem() {
        this.stock.add(new Pickles());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}
