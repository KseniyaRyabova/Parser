import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class OwnerFileParser {

    static Map<Category,Nomenclature> nomenclatures2;
    static List<String> listOfNomenclature = new ArrayList<>();
    static String filename = System.getProperty("user.dir") + "\\src\\main\\resources\\nomenclatures.xlsx";
    private List<Nomenclature> nomenclatures;
    private List<Category> categoryList;
    private Category category1 = Category.OTHER;


    public static void setListOfNomenclature(String filename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        var workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        System.out.println(sheet);
        var rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            System.out.println(row.getCell(1).toString());
            listOfNomenclature.add(row.getCell(1).toString());

        }
        fileInputStream.close();
        listOfNomenclature.remove(0);
        System.out.println(listOfNomenclature);
    }

    /**
     * Получение списка номенклатур в виде ArrayList
     */
    public static List<String> getListOfNomenclature() {
        try {
            setListOfNomenclature(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfNomenclature;
    }

    public List<Nomenclature> parse() {
        AtomicInteger i = new AtomicInteger(0);
        AtomicInteger j = new AtomicInteger(0);
        getListOfNomenclature();
        Map<Category, AtomicInteger> categoryIncrement = new HashMap<>();
        nomenclatures = new ArrayList<>();
        categoryList = List.of(Category.OTHER);
        //один товар прогонять через все категории, далее проверять, сколько характеристик заполнено,
        //там, где заполнено больше всего, к этой категории и принадлежит товар
        listOfNomenclature.forEach(nomenclatureName -> {
                    for (Category category : categoryList) {
                        AtomicInteger increment = new AtomicInteger(0);
                        category.setCharacteristics(nomenclatureName);
                        category.getCharacteristics().forEach((characteristic, s) -> {
                            if (s != null) {
                                increment.getAndIncrement();
                            }
                        });
                        categoryIncrement.put(category, increment);
                        System.out.println("сюда " + categoryIncrement);
                        i.set(0);
                    }
                    Collection<AtomicInteger> incr = categoryIncrement.values();

                    System.out.println("здесь " + incr);
                    Iterator<AtomicInteger> incrI = incr.iterator();
                    AtomicInteger integer = new AtomicInteger(0);
                    while (incrI.hasNext()) {
                        AtomicInteger integer1 = incrI.next();
                        if (integer1.get() > integer.get()) {
                            integer.set(integer1.get());
                        }
                    }
                    Iterator<AtomicInteger> incrI2 = incr.iterator();
                    AtomicInteger newIncr = new AtomicInteger(0);

                    categoryIncrement.forEach((category, atomicInteger) ->
                            {
                                System.out.println("тут " + category + " " + atomicInteger);
                                while (incrI2.hasNext()) {
                                    if (incrI2.next().get() == integer.get()) {
                                        newIncr.getAndIncrement();
                                    }
                                }

                                System.out.println("туда " + newIncr);
                                if (atomicInteger.get() == integer.get() && newIncr.get() == 1) {
                                    category1 = category;
                                }
                            }


                    );
                    category1.setCharacteristics(nomenclatureName);
            nomenclatures.add(new Nomenclature(category1, nomenclatureName, category1.getCharacteristics()));
            category1 = Category.OTHER;
            newIncr.set(0);
                    System.out.println(integer);


                }

        );
        System.out.println("финал " + nomenclatures);

        //есть дофига категорий
        //у них есть характеристики
        //
//        categoryList.forEach(category -> {
//            listOfNomenclature.forEach(name -> nomenclatures.add(new NomenclatureStruct(category, name, category.getCharacteristics(name))));
//        });
        return nomenclatures;
    }

//    void parse2() {
//        nomenclatures = new ArrayList<>();
//        categoryWithPaths.forEach((key, value) -> value.forEach(str -> findNomenclatureAndPrice(key, str)));
//        System.out.println("категории и все остальное:  " + nomenclatures);
//    }

    public static void main(String[] args) {
        OwnerFileParser parser = new OwnerFileParser();
        parser.parse();
    }
}
