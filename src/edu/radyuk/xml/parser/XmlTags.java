package edu.radyuk.xml.parser;

public enum XmlTags {
    PASSENGER_CARRIAGE,
    FREIGHT_CAR,
    RAILWAY_CARRIAGE_ID,
    PASSENGER_NUMBER,
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
