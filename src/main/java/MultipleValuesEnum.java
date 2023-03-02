/**
 * source: https://www.baeldung.com/java-enum-values
 */
public enum MultipleValuesEnum {
    H("Hydrogen", 1, 1.008f),
    HE("Helium", 2, 4.0026f),
    NE("Neon", 10, 20.180f);

    public final String label;
    public final int atomicNumber;
    public final float atomicWeight;

    //enum is notinstantiable and constructors are private default
    MultipleValuesEnum(String label, int atomicNumber, float atomicWeight) {
        this.label = label;
        this.atomicNumber = atomicNumber;
        this.atomicWeight = atomicWeight;
    }

    public String label() {
        return label;
    }

    public int atomicNumber() {
        return atomicNumber;
    }

    public float atomicWeight() {
        return atomicWeight;
    }
}
