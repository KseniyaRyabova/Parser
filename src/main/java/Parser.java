import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Parser {
    private Map<Category, List<String>> categoryWithPaths;
    String url;
    List<Nomenclature> nomenclatures;

    /**
     * метод parse() парсит товары на сайте, объект которого вызывает этот метод.
     * для каждой категории товаров в отдельности вызывается метод findNomenclatureAndPrice()
     * который реализуется в каждом субклассе парсеров.
     */
    void parse() {
        nomenclatures = new ArrayList<>();
        categoryWithPaths.forEach((key, value) -> value.forEach(str -> findNomenclatureAndPrice(key, str)));
        System.out.println("категории и все остальное:  " + nomenclatures);
    }

    public void setCategoryWithPaths(Map<Category, List<String>> categoryWithPaths) {
        this.categoryWithPaths = categoryWithPaths;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    protected abstract void findNomenclatureAndPrice(Category categoryName, String path);

}
