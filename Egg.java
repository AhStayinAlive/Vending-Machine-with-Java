import java.util.ArrayList;

public class Egg extends Item{
    public Egg() {
        super("Egg", 15, 155);
    }

    public void addItem() {
        this.stock.add(new Egg());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}