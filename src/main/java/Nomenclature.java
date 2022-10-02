import java.util.Map;

public class Nomenclature {
    Category category;
    String name;
    Map<Characteristic, String> characteristics;
    String price;

    public Nomenclature(Category category, String name, Map<Characteristic, String> characteristics, String price) {
        this.category = category;
        this.name = name;
        this.characteristics = characteristics;
        this.price = price;
    }

    public Nomenclature(Category category, String name, Map<Characteristic, String> characteristics) {
        this.category = category;
        this.name = name;
        this.characteristics = characteristics;
    }

    @Override
    public String toString() {
        return "\nкатегория=" + category +
                "\nимя товара=" + name +
//                "\nхарактеристики=" + characteristics +
                "\nцена=" + price +
                "\n-----------------";
    }

}
