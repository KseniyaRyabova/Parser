

public abstract class Comparator {

    protected abstract boolean compareCategory(Nomenclature siteResult, Nomenclature ownerResult);
    protected abstract boolean compareCharacteristics(Nomenclature siteResult, Nomenclature ownerResult);
}
