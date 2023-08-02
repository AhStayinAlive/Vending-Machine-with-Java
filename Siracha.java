import java.util.ArrayList;

public class Siracha extends Item{
    public Siracha() {
        super("Siracha", 5, 6);
    }

    public void addItem() {
        this.stock.add(new Siracha());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}