import java.util.ArrayList;

public class BbqSauce extends Item{

    public BbqSauce() {
        super("BBQ Sauce", 16, 110);
        
    }

    public void addItem() {
        this.stock.add(new BbqSauce());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}
