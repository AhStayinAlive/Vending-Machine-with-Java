import java.util.ArrayList;

public class TurkeyPatty extends Item{

    public TurkeyPatty(){
        super("Turkey Patty", 47, 233);
    }

    public void addItem() {
        this.stock.add(new TurkeyPatty());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}
