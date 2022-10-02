import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static PetrovichParser petrovichParse() {
        String session = "62890001b7ea5200019392bb";
        String url ="https://api.retailrocket.ru/api/2.0";
        PetrovichParser petrovichParser = new PetrovichParser(session, url);
//        petrovichParser.setUrl(url);

        Map<Category, List<String>> categoryWithPaths = new HashMap<>();
        categoryWithPaths.put(Category.OTHER,
                List.of("1512"));

//        categoryWithPaths.put(Category.OTHER,
//                List.of("778"));

        petrovichParser.setCategoryWithPaths(categoryWithPaths);
        petrovichParser.parse();
        return petrovichParser;
    }

    public static void main(String[] args) {
        PetrovichParser petrovichParser = petrovichParse();
        OwnerFileParser ownerFileParser = new OwnerFileParser();
        List<Nomenclature> nomenclatures= ownerFileParser.parse();
        Comparator comparator = new HardComparator();
        nomenclatures.forEach(nomenclature -> {
            petrovichParser.nomenclatures.forEach(nomenclature1 -> {
                    if (comparator.compareCategory(nomenclature, nomenclature1)) {
                        nomenclature.category.setCharacteristics(nomenclature.name);
                        nomenclature.category.getCharacteristics().forEach((characteristic, s) -> {
                            nomenclature1.category.setCharacteristics(nomenclature1.name);
                            nomenclature1.category.getCharacteristics().forEach((characteristic1, s1) -> {
                                if (characteristic == characteristic1) {
                                    System.out.println(s + " " + s1 + ": " + s == s1);
                                }
                            });
                        });
//                        comparator.compareCharacteristics(nomenclature.characteristics, nomenclature1.characteristics);
                    }
            });
        });
//        System.out.println(comparator.compareCategory(petrovichParser.nomenclatures, nomenclatures));

    }


}
