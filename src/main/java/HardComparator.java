import java.util.Iterator;
import java.util.List;

public class HardComparator extends Comparator {


    @Override
    protected boolean compareCategory(Nomenclature siteResult, Nomenclature ownerResult) {
        return siteResult.category == ownerResult.category;
    }

    @Override
    protected boolean compareCharacteristics(Nomenclature siteResult, Nomenclature ownerResult) {
        if (!(siteResult.category == ownerResult.category)) {
            return false;
        }

        Iterator<Characteristic> ownerIterator = siteResult.category.characteristics.iterator();
        Iterator<Characteristic> siteIterator = ownerResult.category.characteristics.iterator();

        while (ownerIterator.hasNext()) {
            var a = ownerIterator.next().getCharacteristic(ownerResult.name);
            var b = siteIterator.next().getCharacteristic(siteResult.name);
            System.out.println(a + "|" + b);
            System.out.println(a.equals(b));
            if (!(a.equals(b))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        OwnerFileParser ownerFileParser = new OwnerFileParser();
        List<Nomenclature> ownerNomenclature = ownerFileParser.parse();
        PetrovichParser petrovichParser = Main.petrovichParse();
        HardComparator hardComparator = new HardComparator();
        List<Nomenclature> petrovichNomenclature = petrovichParser.nomenclatures;
        ownerNomenclature.forEach(nomenclature -> {
            petrovichNomenclature.forEach(nomenclature1 ->
                hardComparator.compareCharacteristics(nomenclature, nomenclature1));
        });
    }
}


