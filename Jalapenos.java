import java.util.ArrayList;

public class Jalapenos extends Item{
    public Jalapenos() {
        super("Jalapenos", 17, 28);
    }

    public void addItem() {
        this.stock.add(new Jalapenos());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}