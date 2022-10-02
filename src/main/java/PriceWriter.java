import java.io.IOException;
import java.util.List;

public class PriceWriter {

    public static void writePriceInFile() {
        OwnerFileParser ownerFileParser = new OwnerFileParser();
        List<Nomenclature> ownerNomenclatures = ownerFileParser.parse();
        PetrovichParser petrovichParser = Main.petrovichParse();
        HardComparator hardComparator = new HardComparator();
        List<Nomenclature> petrovichNomenclatures = petrovichParser.nomenclatures;

        for (Nomenclature nomenclatureOfOwner : ownerNomenclatures) {
            for (Nomenclature nomenclatureOfSite : petrovichNomenclatures) {
                if (hardComparator.compareCharacteristics(nomenclatureOfOwner, nomenclatureOfSite)) {
                    try {
                        FileWriter.priceWriter(nomenclatureOfOwner.price, 2, ownerNomenclatures.indexOf(nomenclatureOfOwner) + 1);
                        System.out.println("Записанная цена: " + nomenclatureOfSite.price);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
//            try {
//                FileWriter.priceWriter("Товар не найден", 2, ownerNomenclatures.indexOf(nomenclatureOfOwner) + 1);
//                System.out.println("Товар не найден");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
//
//        ownerNomenclatures.forEach(nomenclature -> {
//            petrovichNomenclatures.forEach(nomenclature1 -> {
//
//                if (hardComparator.compareCharacteristics(nomenclature, nomenclature1)) {
//                try {
//                    FileWriter.priceWriter(nomenclature1.price, 2, ownerNomenclatures.indexOf(nomenclature) + 1);
//                    System.out.println("Записанная цена: " + nomenclature1.price);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }}
//            });
//        });
    }

    public static void main(String[] args) {
        writePriceInFile();
    }
}
