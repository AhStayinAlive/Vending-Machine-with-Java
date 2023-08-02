import java.util.ArrayList;

public class PorkPatty extends Item{

    public PorkPatty() {
        super("Pork Patty", 28, 295);
    }
    
    public void addItem() {
        this.stock.add(new PorkPatty());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
     
}
