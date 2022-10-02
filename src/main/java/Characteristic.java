import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Characteristic {
//        TYPE("^[а-я|А-Я]+ *[а-я]+$(ая|й)+|^[а-я|А-Я]+ для [а-я\\S|а-я]+(?= )|^[а-я|А-Я]+(?= )"),
//    HUMIDITY("(?<= )е\\S* *в\\S*|(?<= )сух\\S*"),
    OTHER(List.of("(?<= )е\\S* *в\\S*", "(?<= )сух\\S*"), List.of("ест вл", "сух"));
    String regexp;
    String characteristic;
    List<String> regexps;
    List<String> characteristicValue;

    Characteristic(List<String> regexps, List<String> characteristicValue) {
        this.regexps = regexps;
        this.characteristicValue = characteristicValue;
    }


    String getCharacteristic(String nomenclatureName) throws NullPointerException {
        Iterator<String> regexpIterator = regexps.listIterator();
        Iterator<String> valueIterator = characteristicValue.listIterator();
        while (regexpIterator.hasNext()) {
            Pattern pattern = Pattern.compile(regexpIterator.next());
            Matcher matcher = pattern.matcher(nomenclatureName);
            if (matcher.find()) {
                this.characteristic = valueIterator.next();
            }
        }
        return characteristic;
    }


    public static void main(String[] args) {
//        List<Characteristic> list = List.of(TYPE, HUMIDITY);
//        String nomenclatureName = "Доска обрезная антисептированная 50х100х6000 мм естественной влажности сорт 1-2 хвойные породы";
//        list.forEach(characteristic -> System.out.println(characteristic.getCharacteristic(nomenclatureName)));

    }
}
