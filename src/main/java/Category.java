import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Category {
//    PILOMATERIALI(List.of(Characteristic.TYPE, Characteristic.HUMIDITY)),
    OTHER(List.of(Characteristic.OTHER));

    List<Characteristic> characteristics;
    Map<Characteristic, String> characteristicsString = new HashMap<>();

    Category(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }


    public void setCharacteristics(String nomenclatureName) {
        characteristics.forEach(characteristic -> {
            System.out.println(characteristic + " " + characteristic.getCharacteristic(nomenclatureName));
            characteristicsString.put(characteristic, characteristic.getCharacteristic(nomenclatureName));
        });
    }

    public Map<Characteristic, String> getCharacteristics() {
        return characteristicsString;
    }

    public static void main(String[] args) {
//        List<Category> categories = List.of(Category.OTHER, Category.PILOMATERIALI);
//        categories.forEach(categories1 ->
//            categories1.setCharacteristics("Доска обрезная антисептированная 50х100х6000 мм естественной влажности сорт 1-2 хвойные породы"));
    }
}
