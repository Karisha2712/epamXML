package edu.radyuk.xml.tags;

public enum XmlTags {
    TRAIN,
    TOTAL_CARRYING_CAPACITY,
    TOTAL_PASSENGERS_NUMBER,
    PASSENGER_CARRIAGE,
    FREIGHT_CAR,
    RAILWAY_CARRIAGE_ID,
    PASSENGERS_NUMBER,
    CARRYING_CAPACITY;

    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";

    @Override
    public String toString() {
        String result = this.name();
        result = result.toLowerCase();
        result = result.replace(UNDERSCORE, HYPHEN);
        return result;
    }
}
