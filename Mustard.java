import java.util.ArrayList;

public class Mustard extends Item{

    public Mustard(){
        super("Mustard", 0, 66);
    }

    public void addItem() {
        this.stock.add(new Mustard());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}
