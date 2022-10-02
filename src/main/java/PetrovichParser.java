import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.*;

import static io.restassured.RestAssured.given;

public class PetrovichParser extends Parser {
    private final String session;

    //проблема: нужно обеспечить сокрытие реализации
    public PetrovichParser(String session, String url) {
        super.url = url;
//        super.url = "https://api.retailrocket.ru/api/2.0";
        this.session = session;
//        categoryWithPaths = new HashMap<>();
//        categoryWithPaths.put(Category.OTHER, List.of("778"));
//        categoryWithPaths.put(Category.PILOMATERIALI, List.of("1512"));
//        nomenclatures = new ArrayList<>();
    }

    @Override
    protected void findNomenclatureAndPrice(Category categoryName, String path) {
        RestAssured.baseURI = getUrl();
        List<String> prices = new ArrayList<>();
            Response response = given()
                    .get(String.format("/recommendation/popular/5565b1696636b450d01838a9" +
                            "/?categoryIds=%s&stockId=spb&session=%s", path, session));
            List<String> names = response.jsonPath().getList("Name");
            List<Number> pricesD = response.jsonPath().getList("Price");

            pricesD.forEach(price -> prices.add(String.valueOf(price)));
            Iterator<String> iNames = names.iterator();
            Iterator<String> iPrices = prices.iterator();
            while (iNames.hasNext() && iPrices.hasNext()) {
                String name = iNames.next();
                categoryName.setCharacteristics(name);
                nomenclatures.add(new Nomenclature(categoryName, name,
                        categoryName.getCharacteristics(), iPrices.next()));
            }
    }

    public static void main(String[] args) {
//        new PetrovichParser("62890001b7ea5200019392bb").parse();
    }
}