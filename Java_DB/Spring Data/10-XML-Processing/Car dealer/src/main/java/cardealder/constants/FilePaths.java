package cardealder.constants;

public class FilePaths {

    public static final String DIRECTORY = "src/main/resources/";

    public static final String IN = "in/";
    public static final String OUT = "out/";

    public static final String XML = ".xml";

    public static final String CARS = DIRECTORY + IN + "cars" + XML;
    public static final String CUSTOMERS = DIRECTORY + IN + "customers" + XML;
    public static final String PARTS = DIRECTORY + IN + "parts" + XML;
    public static final String SUPPLIERS = DIRECTORY + IN + "suppliers" + XML;

    public static final String ORDERED_CUSTOMERS = DIRECTORY + OUT + "ordered-customers" + XML;
    public static final String TOYOTA_CARS = DIRECTORY + OUT + "toyota-cars" + XML;
    public static final String LOCAL_SUPPLIERS = DIRECTORY + OUT + "local-suppliers" + XML;
    public static final String CARS_AND_PARTS = DIRECTORY + OUT + "cars-and-parts" + XML;
    public static final String CUSTOMERS_TOTAL_SALES = DIRECTORY + OUT + "customers-total-sales" + XML;
    public static final String SALES_DISCOUNTS = DIRECTORY + OUT + "sales-discounts" + XML;

}
