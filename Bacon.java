import java.util.ArrayList;

public class Bacon extends Item{

    public Bacon() {
        super("Bacon", 43, 541);
    }
    
    public void addItem() {
        this.stock.add(new Bacon());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}